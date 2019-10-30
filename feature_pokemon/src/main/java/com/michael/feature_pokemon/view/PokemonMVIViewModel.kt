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
                pokemonList = ResultState.Loading
            )
            is PokemonMVIEffect.FetchPokemonList -> currentState.copy(
                pokemonList = effect.result,
                offset = effect.offset,
                isPtrRefresh = false
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
                state.value = reducer(
                    PokemonMVIEffect.FetchPokemonList(result, offset + LIMIT),
                    currentState
                )
            }
        }
    }
}