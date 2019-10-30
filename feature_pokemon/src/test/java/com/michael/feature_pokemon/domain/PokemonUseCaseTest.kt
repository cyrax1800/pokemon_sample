package com.michael.feature_pokemon.domain

import com.michael.api.responses.ResultState
import com.michael.data_source.model.*
import com.michael.data_source.repository.PokemonRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PokemonUseCaseTest {

    private var repository = mockk<PokemonRepository>(relaxed = true)
    private lateinit var useCase: PokemonUseCase

    private val namedApiResource = NamedApiResource("resource", "category", 1, "asdf")

    @Before
    fun setup() {
        useCase = PokemonUseCase(repository)
    }

    @Test
    fun `should return success`() {
        val actual = ResultState.Success(listOf(namedApiResource))
        val result = runBlocking {
            coEvery { repository.getPokemons(0, 0) } answers {
                Response.success(NamedApiResourceList(0, "", "", listOf(namedApiResource)))
            }
            useCase.getPokemonList(0, 0)
        }
        assert(actual.javaClass === result.javaClass)
    }

    @Test
    fun `should return error`() {
        val actual = ResultState.Error("")
        val result = runBlocking {
            coEvery { repository.getPokemons(0, 0) } answers {
                Response.error(
                    400,
                    ResponseBody.create(MediaType.parse("application/json"), "")
                )
            }
            useCase.getPokemonList(0, 0)
        }
        assert(actual.javaClass === result.javaClass)
    }
}