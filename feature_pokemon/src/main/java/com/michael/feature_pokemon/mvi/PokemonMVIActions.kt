package com.michael.feature_pokemon.mvi

import com.michael.mvi_core.BaseActions

sealed class PokemonMVIActions : BaseActions {
    object Refresh : PokemonMVIActions()
    object LoadMore : PokemonMVIActions()
}