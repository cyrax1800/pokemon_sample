package com.michael.feature_pokemon_detail.mvp

import com.michael.api.responses.ResultState
import com.michael.feature_pokemon_detail.domain.PokemonDetailUseCase
import com.michael.lib_core.test.FetchingIdlingResource
import com.michael.lib_core.thread.SchedulerProvider
import com.michael.lib_mvp_core.BasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailPresenterImpl @Inject constructor(
    private val useCase: PokemonDetailUseCase,
    dispatcher: SchedulerProvider
) : BasePresenter<PokemonDetailContract.View>(dispatcher), PokemonDetailContract.Presenter {

    override fun fetchPokemonDetail(name: String) {
        FetchingIdlingResource.begin()
        launch {
            val result = useCase.getPokemonDetail(name)
            when (result) {
                is ResultState.Success -> view?.showPokemonDetail(result.data)
                is ResultState.Error -> view?.showToast(result.error)
            }
            FetchingIdlingResource.complete()
        }
    }

    override fun fetchPokemonSpecies(name: String) {
        FetchingIdlingResource.begin()
        launch {
            val result = useCase.getPokemonSpecies(name)
            FetchingIdlingResource.complete()
            when (result) {
                is ResultState.Success -> {
                    view?.showPokemonSpecies(result.data)
                    fetchPokemonEvolution(result.data.evolutionChain.getLastId())
                }
                is ResultState.Error -> {
                    view?.showToast(result.error)
                }
            }
        }
    }

    override fun fetchPokemonEvolution(id: Int) {
        FetchingIdlingResource.begin()
        launch {
            val result = useCase.getPokemonEvolution(id)
            FetchingIdlingResource.complete()
            when (result) {
                is ResultState.Success -> {
                    view?.showPokemonEvolution(result.data)
                }
                is ResultState.Error -> view?.showToast(result.error)
            }
        }
    }
}