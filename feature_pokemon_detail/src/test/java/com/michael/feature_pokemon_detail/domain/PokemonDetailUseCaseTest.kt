package com.michael.feature_pokemon_detail.domain

import com.michael.api.responses.ResultState
import com.michael.data_source.model.*
import com.michael.data_source.repository.EvolutionRepository
import com.michael.data_source.repository.PokemonRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PokemonDetailUseCaseTest {

    private var repository = mockk<PokemonRepository>(relaxed = true)
    private var evolutionRepository = mockk<EvolutionRepository>(relaxed = true)
    private lateinit var useCase: PokemonDetailUseCase

    private val pokemon = MockData.getPokemon()

    @Before
    fun setup() {
        useCase = PokemonDetailUseCase(repository, evolutionRepository)
    }

    @Test
    fun `getPokemon detail should return success`() {
        // Given
        val name = "charizard"
        val actual = ResultState.Success(pokemon)
        val result = runBlocking {
            coEvery { repository.getPokemon(name) } answers {
                Response.success(pokemon)
            }

            // When
            useCase.getPokemonDetail(name)
        }

        // Then
        assert(actual.javaClass === result.javaClass)
    }

    @Test
    fun `getPokemon detail should return error`() {
        // Given
        val name = "charizard"
        val actual = ResultState.Error("")
        val result = runBlocking {
            coEvery { repository.getPokemon(name) } answers {
                Response.error(
                    400,
                    ResponseBody.create(MediaType.parse("application/json"), "")
                )
            }

            // When
            useCase.getPokemonDetail(name)
        }

        // Then
        assert(actual.javaClass === result.javaClass)
    }

    @Test
    fun `getPokemonSpecies should return success`() {
        // Given
        val name = "charizard"
        val actual = ResultState.Success(MockData.getPokemonSpecies())
        val result = runBlocking {
            coEvery { repository.getPokemonSpecies(name) } answers {
                Response.success(MockData.getPokemonSpecies())
            }

            // When
            useCase.getPokemonSpecies(name)
        }

        // Then
        assert(actual.javaClass === result.javaClass)
    }

    @Test
    fun `getPokemonSpecies should return error`() {
        // Given
        val name = "charizard"
        val actual = ResultState.Error("")
        val result = runBlocking {
            coEvery { repository.getPokemonSpecies(name) } answers {
                Response.error(
                    400,
                    ResponseBody.create(MediaType.parse("application/json"), "")
                )
            }

            // When
            useCase.getPokemonSpecies(name)
        }

        // Then
        assert(actual.javaClass === result.javaClass)
    }

    @Test
    fun `getPokemonEvolution should return success`() {
        // Given
        val mockPokemon = MockData.getPokemon()
        val mockEvolution = MockData.getEvolutionChain()
        val actual = ResultState.Success(listOf(
            mockPokemon
        ))
        val result = runBlocking {
            coEvery { evolutionRepository.getPokemonEvolution(1) } answers {
                Response.success(mockEvolution)
            }
            coEvery { repository.getPokemon(mockEvolution.chain.species.name) } answers {
                Response.success(mockPokemon)
            }

            // When
            useCase.getPokemonEvolution(1)
        }

        // Then
        assert(actual.javaClass === result.javaClass)
    }

    @Test
    fun `getPokemonEvolution should return error`() {
        // Given
        val actual = ResultState.Error("")
        val result = runBlocking {
            coEvery { evolutionRepository.getPokemonEvolution(1) } answers {
                Response.error(
                    400,
                    ResponseBody.create(MediaType.parse("application/json"), "")
                )
            }

            // When
            useCase.getPokemonEvolution(1)
        }

        // Then
        assert(actual.javaClass === result.javaClass)
    }

    @Test
    fun `getPokemonEvolution when fetch pokemon error should return error`() {
        // Given
        val mockEvolution = MockData.getEvolutionChain()
        val actual = ResultState.Error("")
        val result = runBlocking {
            coEvery { evolutionRepository.getPokemonEvolution(1) } answers {
                Response.success(mockEvolution)
            }
            coEvery { repository.getPokemon(mockEvolution.chain.species.name) } answers {
                Response.error(
                    400,
                    ResponseBody.create(MediaType.parse("application/json"), "")
                )
            }

            // When
            useCase.getPokemonEvolution(1)
        }

        // Then
        assert(actual.javaClass === result.javaClass)
    }
}