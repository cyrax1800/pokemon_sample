package com.michael.feature_pokemon.di

import com.michael.api.Api
import com.michael.feature_pokemon.domain.PokemonUseCase
import com.michael.feature_pokemon.mvi.PokemonState
import com.michael.data_source.di.DataScope
import com.michael.data_source.repository.PokemonRepository
import com.michael.data_source.repository.PokemonRepositoryImpl
import com.michael.data_source.service.PokemonService
import com.michael.lib_core.thread.ApplicationSchedulerProvider
import com.michael.lib_core.thread.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class PokemonModule {

    @DataScope
    @Provides
    fun providePokemonService(): PokemonService {
        return Api.service(PokemonService::class)
    }

    @PokemonScope
    @Provides
    fun provideRepository(@DataScope service: PokemonService): PokemonRepository {
        return PokemonRepositoryImpl(service)
    }

    @PokemonScope
    @Provides
    fun provideUseCase(repository: PokemonRepository): PokemonUseCase {
        return PokemonUseCase(repository)
    }

    @PokemonScope
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = ApplicationSchedulerProvider()

    @PokemonScope
    @Provides
    fun provideMainActivityState(): PokemonState = PokemonState()
}