package com.michael.feature_pokemon.di

import com.michael.feature_pokemon.view.PokemonActivity
import dagger.Component

@PokemonScope
@Component(
    modules = [
        PokemonModule::class,
        PokemonViewModelModule::class
    ]
)
interface PokemonComponent {
    fun inject(activity: PokemonActivity)
}