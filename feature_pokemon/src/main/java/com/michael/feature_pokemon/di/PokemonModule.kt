package com.michael.feature_pokemon.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.michael.data_source.di.DataModule
import com.michael.feature_pokemon.domain.PokemonUseCase
import com.michael.feature_pokemon.mvi.PokemonState
import com.michael.data_source.di.DataScope
import com.michael.data_source.repository.PokemonRepository
import com.michael.data_source.repository.PokemonRepositoryImpl
import com.michael.data_source.service.PokemonService
import com.michael.feature_pokemon.data.PokemonPref
import com.michael.lib_core.thread.ApplicationSchedulerProvider
import com.michael.lib_core.thread.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class PokemonModule(val context: Context) {

    @PokemonScope
    @Provides
    fun provideRepository(@DataScope service: PokemonService): PokemonRepository {
        return PokemonRepositoryImpl(service)
    }

    @PokemonScope
    @Provides
    fun providePokemonPref(): PokemonPref {
        return PokemonPref(context.getSharedPreferences("PokemonPref", MODE_PRIVATE))
    }

    @PokemonScope
    @Provides
    fun provideUseCase(repository: PokemonRepository, pokemonPref: PokemonPref): PokemonUseCase {
        return PokemonUseCase(repository, pokemonPref)
    }

    @PokemonScope
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = ApplicationSchedulerProvider()

    @PokemonScope
    @Provides
    fun provideMainActivityState(): PokemonState = PokemonState()
}