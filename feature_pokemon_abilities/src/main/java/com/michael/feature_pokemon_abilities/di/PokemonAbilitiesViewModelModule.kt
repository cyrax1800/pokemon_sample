package com.michael.feature_pokemon_abilities.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.michael.feature_pokemon_abilities.view.PokemonAbilitiesViewModel
import com.michael.lib_core.viewmodel.ViewModelFactory
import com.michael.lib_core.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PokemonAbilitiesViewModelModule {

    @PokemonAbilitiesScope
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PokemonAbilitiesViewModel::class)
    abstract fun bindPokemonAbilitiesViewModel(viewModel: PokemonAbilitiesViewModel): ViewModel
}
