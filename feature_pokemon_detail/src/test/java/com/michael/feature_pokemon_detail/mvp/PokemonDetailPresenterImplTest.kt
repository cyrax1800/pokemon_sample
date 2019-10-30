package com.michael.feature_pokemon_detail.mvp

import androidx.lifecycle.LifecycleRegistry
import com.michael.api.responses.ResultState
import com.michael.data_source.model.ApiResource
import com.michael.data_source.model.EvolutionChain
import com.michael.data_source.model.MockData
import com.michael.data_source.model.ResourceSummary
import com.michael.feature_pokemon_detail.domain.PokemonDetailUseCase
import com.michael.lib_core.thread.TestSchedulerProvider
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class PokemonDetailPresenterImplTest {

    val useCase = mockk<PokemonDetailUseCase>(relaxed = true)
    val view = mockk<PokemonDetailContract.View>()
    private lateinit var presenter: PokemonDetailContract.Presenter

    private val lifecycle = LifecycleRegistry(mockk(relaxed = true))
    private val schedulerProvider = TestSchedulerProvider()

    @Before
    fun setup() {
        Dispatchers.setMain(schedulerProvider.ui())
        presenter = PokemonDetailPresenterImpl(useCase, schedulerProvider)

        presenter.attach(view, lifecycle)
    }

    @Test
    fun `fetchPokemonDetail success should show PokemonDetail`() {
        // Given
        val name = "name"
        val pokemon = MockData.getPokemon()
        every {
            view.showPokemonDetail(any())
        } returns Unit
        coEvery {
            useCase.getPokemonDetail(name)
        } answers { ResultState.Success(pokemon) }

        // When
        presenter.fetchPokemonDetail(name)

        // Then
        verify(exactly = 1) {
            view.showPokemonDetail(pokemon)
        }
    }

    @Test
    fun `fetchPokemonDetail error should show toast`() {
        // Given
        val name = "name"
        every {
            view.showToast(any())
        } returns Unit
        coEvery {
            useCase.getPokemonDetail(name)
        } answers { ResultState.Error(name) }

        // When
        presenter.fetchPokemonDetail(name)

        // Then
        verify(exactly = 1) {
            view.showToast(name)
        }
    }

    @Test
    fun `fetchPokemonSpecies error should show toast`() {
        // Given
        val name = "name"
        every {
            view.showToast(any())
        } returns Unit
        coEvery {
            useCase.getPokemonSpecies(name)
        } answers { ResultState.Error(name) }

        // When
        presenter.fetchPokemonSpecies(name)

        // Then
        verify(exactly = 1) {
            view.showToast(name)
        }
    }

    @Test
    fun `fetchPokemonSpecies success should show PokemonSpecies`() {
        // Given
        val name = "name"
        val pokemonSpecies = MockData.getPokemonSpecies()
            .copy(evolutionChain = ApiResource("asdf", 1, "asdf/1"))
        every { view.showPokemonSpecies(any()) } returns Unit
        every { view.showPokemonEvolution(any()) } returns Unit
        coEvery {
            useCase.getPokemonSpecies(name)
        } answers { ResultState.Success(pokemonSpecies) }
        coEvery {
            useCase.getPokemonEvolution(any())
        } answers { ResultState.Success(listOf(MockData.getPokemon())) }

        // When
        presenter.fetchPokemonSpecies(name)

        // Then
        verify(exactly = 1) {
            view.showPokemonSpecies(pokemonSpecies)
        }
    }

    @Test
    fun `fetchPokemonEvolution success should show showPokemonEvolution`() {
        // Given
        val listPokemon = listOf(MockData.getPokemon())
        every { view.showPokemonEvolution(any()) } returns Unit
        coEvery {
            useCase.getPokemonEvolution(any())
        } answers { ResultState.Success(listPokemon) }

        // When
        presenter.fetchPokemonEvolution(1)

        // Then
        verify(exactly = 1) {
            view.showPokemonEvolution(listPokemon)
        }
    }

    @Test
    fun `fetchPokemonEvolution error should show toast`() {
        // Given
        every { view.showToast(any()) } returns Unit
        coEvery {
            useCase.getPokemonEvolution(any())
        } answers { ResultState.Error("") }

        // When
        presenter.fetchPokemonEvolution(1)

        // Then
        verify(exactly = 1) {
            view.showToast("")
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}