package com.michael.feature_pokemon_abilities.view

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.michael.api.responses.ResultState
import com.michael.data_source.model.Ability
import com.michael.data_source.model.MockData
import com.michael.feature_pokemon_abilities.domain.PokemonAbilityUseCase
import com.michael.lib_core.thread.TestSchedulerProvider
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class PokemonAbilitiesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    var pokemonAbilitesObs: Observer<Map<String, ResultState<Ability>>> = mockk(relaxed = true)
    var errorObs: Observer<String> = mockk(relaxed = true)

    var useCase: PokemonAbilityUseCase = mockk(relaxed = true)

    private lateinit var viewModel: PokemonAbilitiesViewModel

    private val schedulerProvider = TestSchedulerProvider()

    @Before
    fun setup() {
        Dispatchers.setMain(schedulerProvider.ui())

        viewModel = PokemonAbilitiesViewModel(useCase, schedulerProvider)
        viewModel.pokemonAbilities.observeForever(pokemonAbilitesObs)
        viewModel.error.observeForever(errorObs)

        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
    }

    @Test
    fun `should return an error message`() = runBlocking {
        // Given
        val name1 = "name1"
        val listName = listOf(name1)
        val returnValue = ResultState.Error("message")
        coEvery { useCase.getPokemonAbility(any()) } answers {
            returnValue
        }

        // When
        viewModel.getPokemonAbilities(listName)

        // Then
        val slot = slot<String>()
        verify(exactly = 1) {
            errorObs.onChanged(capture(slot))
        }
        Assert.assertEquals(slot.captured, "message")
    }

    @Test
    fun `should return an allComplete list`() = runBlocking {
        // Given
        val name1 = "name1"
        val name2 = "name2"
        val listName = listOf(name1, name2)
        val returnValue = ResultState.Success(MockData.getAbility())
        coEvery { useCase.getPokemonAbility(any()) } answers {
            returnValue
        }

        // When
        viewModel.getPokemonAbilities(listName)

        // Then
        val listValue = arrayListOf<Map<String, ResultState<Ability>>>()
        verify {
            pokemonAbilitesObs.onChanged(capture(listValue))
        }
        val lastValue = listValue.last()
        lastValue.forEach {
            Assert.assertEquals(it.component2().javaClass, returnValue.javaClass)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}