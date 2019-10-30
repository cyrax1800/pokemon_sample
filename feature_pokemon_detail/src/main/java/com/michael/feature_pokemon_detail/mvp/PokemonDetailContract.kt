package com.michael.feature_pokemon_detail.mvp

import com.michael.data_source.model.EvolutionChain
import com.michael.data_source.model.Pokemon
import com.michael.data_source.model.PokemonSpecies
import com.michael.lib_mvp_core.BaseContract

interface PokemonDetailContract {
    interface View : BaseContract.View {
        fun showPokemonDetail(pokemon: Pokemon)
        fun showPokemonSpecies(species: PokemonSpecies)
        fun showPokemonEvolution(pokemonList: List<Pokemon>)
        fun showToast(message: String?)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun fetchPokemonDetail(name: String)
        fun fetchPokemonSpecies(name: String)
        fun fetchPokemonEvolution(id: Int)
    }
}