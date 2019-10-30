package com.michael.feature_pokemon_detail.domain

import com.michael.api.responses.ResultState
import com.michael.data_source.model.ChainLink
import com.michael.data_source.model.EvolutionChain
import com.michael.data_source.model.Pokemon
import com.michael.data_source.model.PokemonSpecies
import com.michael.data_source.repository.EvolutionRepository
import com.michael.data_source.repository.PokemonRepository
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val evolutionRepository: EvolutionRepository
) {

    suspend fun getPokemonDetail(name: String): ResultState<Pokemon> {
        return try {
            val response = pokemonRepository.getPokemon(name)
            if (response.isSuccessful) {
                var result = response.body()!!
                result = result.copy(types = result.types.sortedBy {
                    it.slot
                })
                ResultState.Success(result)
            } else {
                ResultState.Error(response.message())
            }
        } catch (e: Throwable) {
            ResultState.Error(e.message)
        }
    }

    suspend fun getPokemonSpecies(name: String): ResultState<PokemonSpecies> {
        return try {
            val response = pokemonRepository.getPokemonSpecies(name)
            if (response.isSuccessful) {
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error(response.message())
            }
        } catch (e: Throwable) {
            ResultState.Error(e.message)
        }
    }

    suspend fun getPokemonEvolution(id: Int): ResultState<List<Pokemon>> {
        val listPokemon = arrayListOf<Pokemon>()
        val listPokemonName = arrayListOf<String>()
        lateinit var pokemonEvolutionChain: EvolutionChain
        var resultState: ResultState<Any>
        resultState = try {
            val response = evolutionRepository.getPokemonEvolution(id)
            if (response.isSuccessful) {
                pokemonEvolutionChain = response.body()!!
                ResultState.Success(pokemonEvolutionChain)
            } else {
                ResultState.Error(response.message())
            }
        } catch (e: Throwable) {
            ResultState.Error(e.message)
        }

        if (resultState is ResultState.Success) {
            getAllPokemonListName(listPokemonName, pokemonEvolutionChain.chain)
        }

        listPokemonName.forEach { name ->
            resultState = try {
                val response = pokemonRepository.getPokemon(name)
                if (response.isSuccessful) {
                    val result = response.body()!!
                    listPokemon.add(result)
                    ResultState.Success(result)
                } else {
                    ResultState.Error(response.message())
                }
            } catch (e: Throwable) {
                ResultState.Error(e.message)
            }
        }

        if (resultState is ResultState.Error) {
            return ResultState.Error((resultState as ResultState.Error).error)
        }
        return ResultState.Success(listPokemon)
    }

    private fun getAllPokemonListName(list: ArrayList<String>, chainLink: ChainLink) {
        list.add(chainLink.species.name)
        if (chainLink.evolvesTo.isNotEmpty()) {
            getAllPokemonListName(list, chainLink.evolvesTo.first())
        }
    }
}