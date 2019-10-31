package com.michael.feature_pokemon_abilities.di

import com.michael.data_source.di.DataModule
import com.michael.data_source.di.DataScope
import com.michael.data_source.repository.PokemonRepository
import com.michael.data_source.repository.PokemonRepositoryImpl
import com.michael.data_source.service.PokemonService
import com.michael.feature_pokemon_abilities.domain.PokemonAbilityUseCase
import com.michael.lib_core.thread.ApplicationSchedulerProvider
import com.michael.lib_core.thread.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class PokemonAbilitiesModule {

    @PokemonAbilitiesScope
    @Provides
    fun provideRepository(@DataScope service: PokemonService): PokemonRepository {
        return PokemonRepositoryImpl(service)
    }

    @PokemonAbilitiesScope
    @Provides
    fun provideUseCase(repository: PokemonRepository): PokemonAbilityUseCase {
        return PokemonAbilityUseCase(repository)
    }

    @PokemonAbilitiesScope
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = ApplicationSchedulerProvider()
}