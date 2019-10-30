package com.michael.feature_pokemon_detail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.michael.feature_pokemon_detail.mvp.PokemonDetailPresenterImpl
import com.michael.lib_core.viewmodel.ViewModelFactory
import com.michael.lib_core.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PokemonDetailPresenterModule {

    @PokemonDetailScope
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailPresenterImpl::class)
    abstract fun bindPokemonDetailViewModel(viewModel: PokemonDetailPresenterImpl): ViewModel
}
