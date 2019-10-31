package com.michael.feature_pokemon_detail.di

import com.michael.data_source.di.DataModule
import com.michael.data_source.di.DataScope
import com.michael.data_source.repository.EvolutionRepository
import com.michael.data_source.repository.EvolutionRepositoryImpl
import com.michael.data_source.repository.PokemonRepository
import com.michael.data_source.repository.PokemonRepositoryImpl
import com.michael.data_source.service.EvolutionService
import com.michael.data_source.service.PokemonService
import com.michael.feature_pokemon_detail.domain.PokemonDetailUseCase
import com.michael.feature_pokemon_detail.mvp.PokemonDetailContract
import com.michael.feature_pokemon_detail.mvp.PokemonDetailPresenterImpl
import com.michael.lib_core.thread.ApplicationSchedulerProvider
import com.michael.lib_core.thread.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class PokemonDetailModule(private val view: PokemonDetailContract.View) {

    @PokemonDetailScope
    @Provides
    fun providePokemonRepository(@DataScope service: PokemonService): PokemonRepository {
        return PokemonRepositoryImpl(service)
    }

    @PokemonDetailScope
    @Provides
    fun provideEvolutionRepository(@DataScope service: EvolutionService): EvolutionRepository {
        return EvolutionRepositoryImpl(service)
    }

    @PokemonDetailScope
    @Provides
    fun provideUseCase(
        pokemonRepository: PokemonRepository,
        evolutionRepository: EvolutionRepository
    ): PokemonDetailUseCase {
        return PokemonDetailUseCase(pokemonRepository, evolutionRepository)
    }

    @PokemonDetailScope
    @Provides
    fun providePokemonDetailPresenter(
        @PokemonDetailScope useCase: PokemonDetailUseCase,
        @PokemonDetailScope dispatcher: SchedulerProvider
    ): PokemonDetailContract.Presenter {
        return PokemonDetailPresenterImpl(useCase, dispatcher)
    }

    @PokemonDetailScope
    @Provides
    fun providePokemonDetailView(): PokemonDetailContract.View {
        return view
    }

    @PokemonDetailScope
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = ApplicationSchedulerProvider()
}