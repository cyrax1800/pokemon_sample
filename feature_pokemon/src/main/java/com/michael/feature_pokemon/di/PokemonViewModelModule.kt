package com.michael.feature_pokemon.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.michael.feature_pokemon.view.PokemonMVIViewModel
import com.michael.lib_core.viewmodel.ViewModelFactory
import com.michael.lib_core.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PokemonViewModelModule {

    @PokemonScope
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PokemonMVIViewModel::class)
    abstract fun bindPokemonMVIViewModel(viewModel: PokemonMVIViewModel): ViewModel
}
