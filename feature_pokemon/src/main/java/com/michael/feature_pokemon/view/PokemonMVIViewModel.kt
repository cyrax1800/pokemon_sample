package com.michael.feature_pokemon.view

import android.util.Log
import com.michael.api.responses.ResultState
import com.michael.feature_pokemon.domain.PokemonUseCase
import com.michael.feature_pokemon.mvi.PokemonMVIActions
import com.michael.feature_pokemon.mvi.PokemonState
import com.michael.feature_pokemon.mvi.PokemonMVIEffect
import com.michael.lib_core.Consts.LIMIT
import com.michael.lib_core.test.FetchingIdlingResource
import com.michael.lib_core.thread.SchedulerProvider
import com.michael.mvi_core.BaseMVIViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonMVIViewModel @Inject constructor(
    private val useCase: PokemonUseCase,
    dispatcher: SchedulerProvider,
    initialState: PokemonState
) : BaseMVIViewModel<PokemonMVIActions, PokemonState, PokemonMVIEffect>(
    dispatcher,
    initialState
), PokemonListContract {

    override fun dispatch(actions: PokemonMVIActions) {
        super.dispatch(actions)
        when (actions) {
            is PokemonMVIActions.LoadMore -> {
                getPokemonList(currentState.offset)
            }
            is PokemonMVIActions.Refresh -> {
                state.value = reducer(PokemonMVIEffect.Refresh, currentState)
                getPokemonList(0)
            }
            is PokemonMVIActions.Clicked -> {
                useCase.updatePokemonName(actions.pokemon)
            }
        }
    }

    override fun reducer(
        effect: PokemonMVIEffect,
        currentState: PokemonState
    ): PokemonState {
        Log.d(tag, "Effect that triggered: $effect")
        return when (effect) {
            is PokemonMVIEffect.Refresh -> currentState.copy(
                isPtrRefresh = true
            )
            is PokemonMVIEffect.Load -> currentState.copy(
                isLoading = true
            )
            is PokemonMVIEffect.FetchPokemonList -> {
                currentState.copy(
                    pokemonList = effect.result,
                    isLoading = false,
                    offset = effect.offset,
                    isPtrRefresh = false
                )
            }
            is PokemonMVIEffect.FetchPokemonError -> currentState.copy(
                isLoading = false,
                isPtrRefresh = false,
                errorMessage = effect.message
            )
        }
    }

    override fun getPokemonList(offset: Int) {
        FetchingIdlingResource.begin()
        state.value = reducer(PokemonMVIEffect.Load, currentState)
        launch {
            val result = useCase.getPokemonList(LIMIT, offset)
            withContext(Dispatchers.Main) {
                FetchingIdlingResource.complete()
                if (result is ResultState.Success) {
                    val list = if (offset == 0) {
                        result.data.results
                    } else {
                        state.value!!.pokemonList + result.data.results
                    }
                    state.value = reducer(
                        PokemonMVIEffect.FetchPokemonList(list, offset + LIMIT),
                        currentState
                    )
                } else if (result is ResultState.Error) {
                    state.value = reducer(
                        PokemonMVIEffect.FetchPokemonError(result.error),
                        currentState
                    )
                }
            }
        }
    }
}