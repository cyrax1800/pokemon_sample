package com.michael.feature_pokemon.mvi

import com.michael.api.responses.ResultState
import com.michael.data_source.model.NamedApiResourceList

sealed class PokemonMVIEffect {
    object Refresh : PokemonMVIEffect()
    object Load : PokemonMVIEffect()
    data class FetchPokemonList(
        val result: ResultState<NamedApiResourceList>,
        val offset: Int
    ) : PokemonMVIEffect()
}