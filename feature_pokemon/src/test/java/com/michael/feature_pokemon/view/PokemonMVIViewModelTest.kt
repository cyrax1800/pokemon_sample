package com.michael.feature_pokemon.view

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.michael.api.responses.ResultState
import com.michael.data_source.model.NamedApiResource
import com.michael.data_source.model.NamedApiResourceList
import com.michael.feature_pokemon.domain.PokemonUseCase
import com.michael.feature_pokemon.mvi.PokemonMVIActions
import com.michael.feature_pokemon.mvi.PokemonState
import com.michael.lib_core.thread.TestSchedulerProvider
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class PokemonMVIViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    var state: Observer<PokemonState> = mockk(relaxed = true)

    var useCase: PokemonUseCase = mockk(relaxed = true)

    private lateinit var viewModel: PokemonMVIViewModel
    private lateinit var pokemonState: PokemonState

    private val resources = listOf(
        NamedApiResource("resource", "category", 1, "asdf")
    )

    private val pokemonListData = NamedApiResourceList(1, "", "", resources)
    private val schedulerProvider = TestSchedulerProvider()

    @Before
    fun setup() {
        Dispatchers.setMain(schedulerProvider.ui())

        pokemonState = PokemonState()
        viewModel = PokemonMVIViewModel(useCase, schedulerProvider, pokemonState)
        viewModel.observableState.observeForever(state)

        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
    }

    @Test
    fun `should return an error message`() = runBlocking {
        // Given
        val returnValue = ResultState.Error("message")
        coEvery { useCase.getPokemonList(20, 0) } answers {
            returnValue
        }

        // When
        viewModel.getPokemonList(0)

        // Then
        val listValue = arrayListOf<PokemonState>()
        verify {
            state.onChanged(capture(listValue))
        }
        Assert.assertEquals(listValue.last().pokemonState.javaClass, returnValue.javaClass)
        Assert.assertEquals(
            (listValue.last().pokemonState as ResultState.Error).error,
            returnValue.error
        )
    }

    @Test
    fun `should return a response of NamedApiResourceList`() = runBlocking {
        // Given
        val returnValue = ResultState.Success(pokemonListData)
        coEvery { useCase.getPokemonList(20, 0) } answers {
            returnValue
        }

        // When
        viewModel.getPokemonList(0)

        // Then
        val listValue = arrayListOf<PokemonState>()
        verify {
            state.onChanged(capture(listValue))
        }
        Assert.assertEquals(listValue.last().pokemonState.javaClass, returnValue.javaClass)
        Assert.assertEquals(
            (listValue.last().pokemonState as ResultState.Success).data,
            returnValue.data
        )
    }

    @Test
    fun `dispatch load more should increase offset`() {
        // Given
        val returnValue = ResultState.Success(pokemonListData)
        coEvery { useCase.getPokemonList(20, 20) } answers {
            returnValue
        }
        pokemonState = PokemonState().copy(offset = 20)
        viewModel = PokemonMVIViewModel(useCase, schedulerProvider, pokemonState)
        viewModel.observableState.observeForever(state)

        // When
        viewModel.dispatch(PokemonMVIActions.LoadMore)

        // Then
        val listValue = arrayListOf<PokemonState>()
        verify {
            state.onChanged(capture(listValue))
        }
        Assert.assertEquals(listValue.last().pokemonState.javaClass, returnValue.javaClass)
        Assert.assertEquals(listValue.last().offset, 40)
    }

    @Test
    fun `dispatch refresh should reset offset`() {
        // Given
        val returnValue = ResultState.Success(pokemonListData)
        coEvery { useCase.getPokemonList(20, 0) } answers {
            returnValue
        }
        pokemonState = PokemonState().copy(offset = 20)
        viewModel = PokemonMVIViewModel(useCase, schedulerProvider, pokemonState)
        viewModel.observableState.observeForever(state)

        // When
        viewModel.dispatch(PokemonMVIActions.Refresh)

        // Then
        val listValue = arrayListOf<PokemonState>()
        verify {
            state.onChanged(capture(listValue))
        }
        Assert.assertEquals(listValue.last().pokemonState.javaClass, returnValue.javaClass)
        Assert.assertEquals(listValue.last().offset, 20)
    }

    @Test
    fun `dispatch refresh should have set isPtrRefresh true`() {
        // Given
        val returnValue = ResultState.Success(pokemonListData)
        coEvery { useCase.getPokemonList(20, 0) } answers {
            returnValue
        }
        pokemonState = PokemonState().copy(offset = 20)
        viewModel = PokemonMVIViewModel(useCase, schedulerProvider, pokemonState)
        viewModel.observableState.observeForever(state)

        // When
        viewModel.dispatch(PokemonMVIActions.Refresh)

        // Then
        val listValue = arrayListOf<PokemonState>()
        verify {
            state.onChanged(capture(listValue))
        }
        Assert.assertEquals(listValue.first().isPtrRefresh, true)
        Assert.assertEquals(listValue.last().isPtrRefresh, false)
    }

    @Test
    fun `dispatch Clicked should have trigger updatePokemonName`() {
        // Given
        every { useCase.updatePokemonName(any()) } returns Unit

        // When
        viewModel.dispatch(PokemonMVIActions.Clicked(resources.first()))

        // Then
        verify(exactly = 1) {
            useCase.updatePokemonName(match {
                it == resources.first()
            })
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}