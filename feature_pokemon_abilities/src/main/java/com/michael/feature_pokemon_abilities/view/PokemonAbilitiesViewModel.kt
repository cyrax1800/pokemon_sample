package com.michael.feature_pokemon_abilities.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michael.api.responses.ResultState
import com.michael.data_source.model.Ability
import com.michael.feature_pokemon_abilities.domain.PokemonAbilityUseCase
import com.michael.lib_core.test.FetchingIdlingResource
import com.michael.lib_core.thread.SchedulerProvider
import com.michael.lib_core.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonAbilitiesViewModel @Inject constructor(
    private val useCase: PokemonAbilityUseCase,
    dispatcher: SchedulerProvider
) : BaseViewModel(dispatcher), PokemonAbilitiesContract {

    private val mPokemonMap = HashMap<String, ResultState<Ability>>()

    private val mPokemonAbilities = MutableLiveData<Map<String, ResultState<Ability>>>()
    val pokemonAbilities: LiveData<Map<String, ResultState<Ability>>>
        get() = mPokemonAbilities

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    override fun getPokemonAbilities(list: List<String>) {
        FetchingIdlingResource.begin()
        mPokemonMap.clear()
        list.forEach {
            mPokemonMap[it] = ResultState.Loading
            mPokemonAbilities.value = mPokemonMap
            launch {
                val result = useCase.getPokemonAbility(it)
                mPokemonMap[it] = result
                withContext(Dispatchers.Main) {
                    when (result) {
                        is ResultState.Error -> _error.postValue(result.error)
                        else -> mPokemonAbilities.postValue(mPokemonMap)
                    }
                    var hasLoading = false
                    mPokemonMap.forEach { map ->
                        if (map.value is ResultState.Loading) {
                            hasLoading = true
                        }
                    }
                    if (!hasLoading) {
                        FetchingIdlingResource.complete()
                    }
                }
            }
        }
    }
}