package com.michael.feature_pokemon.mvi

import com.michael.data_source.model.NamedApiResource
import com.michael.mvi_core.BaseActions

sealed class PokemonMVIActions : BaseActions {
    object Refresh : PokemonMVIActions()
    object LoadMore : PokemonMVIActions()
    data class Clicked(val pokemon: NamedApiResource) : PokemonMVIActions()
}