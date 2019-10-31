package com.michael.data_source.di

import com.michael.api.Api
import com.michael.data_source.service.EvolutionService
import com.michael.data_source.service.PokemonService
import dagger.Module
import dagger.Provides

@Module
class DataModule  {

    @DataScope
    @Provides
    fun providePokemonService(): PokemonService {
        return Api.service(PokemonService::class)
    }

    @DataScope
    @Provides
    fun provideEvolutionService(): EvolutionService {
        return Api.service(EvolutionService::class)
    }

}