package com.michael.feature_pokemon_abilities.domain

import com.michael.api.responses.ResultState
import com.michael.data_source.model.MockData
import com.michael.data_source.repository.PokemonRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PokemonAbilityUseCaseTest {

    private var repository = mockk<PokemonRepository>(relaxed = true)
    private lateinit var useCase: PokemonAbilityUseCase

    @Before
    fun setup() {
        useCase = PokemonAbilityUseCase(repository)
    }

    @Test
    fun `should return success`() {
        val ability = MockData.getAbility()
        val name = "abilityname"
        val actual = ResultState.Success(ability)
        val result = runBlocking {
            coEvery { repository.getPokemonAbility(name) } answers {
                Response.success(ability)
            }
            useCase.getPokemonAbility(name)
        }
        assert(actual.javaClass === result.javaClass)
    }

    @Test
    fun `should return error`() {
        val name = "abilityname"
        val actual = ResultState.Error("")
        val result = runBlocking {
            coEvery { repository.getPokemonAbility(name) } answers {
                Response.error(
                    400,
                    ResponseBody.create(MediaType.parse("application/json"), "")
                )
            }
            useCase.getPokemonAbility(name)
        }
        assert(actual.javaClass === result.javaClass)
    }
}