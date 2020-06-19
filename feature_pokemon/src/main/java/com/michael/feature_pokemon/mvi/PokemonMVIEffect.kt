package com.michael.feature_pokemon.mvi

import com.michael.data_source.model.NamedApiResource

sealed class PokemonMVIEffect {
    object Refresh : PokemonMVIEffect()
    object Load : PokemonMVIEffect()
    data class FetchPokemonList(
        val result: List<NamedApiResource>,
        val offset: Int
    ) : PokemonMVIEffect()

    data class FetchPokemonError(val message: String?) : PokemonMVIEffect()
}