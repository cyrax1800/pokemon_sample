package com.michael.feature_pokemon_abilities.di

import com.michael.feature_pokemon_abilities.view.PokemonAbilitiesActivity
import dagger.Component

@PokemonAbilitiesScope
@Component(
    modules = [
        PokemonAbilitiesModule::class,
        PokemonAbilitiesViewModelModule::class
    ]
)
interface PokemonAbilitiesComponent {
    fun inject(activity: PokemonAbilitiesActivity)
}