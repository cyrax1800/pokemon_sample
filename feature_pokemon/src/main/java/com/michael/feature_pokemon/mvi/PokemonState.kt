package com.michael.feature_pokemon.mvi

import com.michael.api.responses.ResultState
import com.michael.data_source.model.NamedApiResourceList
import com.michael.mvi_core.BaseState

data class PokemonState(
    val pokemonList: ResultState<NamedApiResourceList> = ResultState.Loading,
    val offset: Int = 0,
    val isPtrRefresh: Boolean = false
) : BaseState