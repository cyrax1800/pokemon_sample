package com.michael.feature_pokemon_detail.di

import com.michael.feature_pokemon_detail.view.PokemonDetailActivity
import dagger.Component

@PokemonDetailScope
@Component(
    modules = [
        PokemonDetailModule::class,
        PokemonDetailPresenterModule::class
    ]
)
interface PokemonDetailComponent {
    fun inject(activitty: PokemonDetailActivity)
}