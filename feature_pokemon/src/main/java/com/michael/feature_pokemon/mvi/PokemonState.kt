package com.michael.feature_pokemon.mvi

import com.michael.data_source.model.NamedApiResource
import com.michael.mvi_core.BaseState

data class PokemonState(
    val pokemonList: List<NamedApiResource> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val offset: Int = 0,
    val isPtrRefresh: Boolean = false
) : BaseState