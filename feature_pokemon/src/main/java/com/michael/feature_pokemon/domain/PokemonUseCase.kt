package com.michael.feature_pokemon.domain

import com.michael.api.responses.ResultState
import com.michael.data_source.model.NamedApiResourceList
import com.michael.data_source.repository.PokemonRepository
import javax.inject.Inject

class PokemonUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend fun getPokemonList(limit: Int, offset: Int): ResultState<NamedApiResourceList> {
        return try {
            val response = repository.getPokemons(limit, offset)
            if (response.isSuccessful) {
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error(response.message())
            }
        } catch (e: Throwable) {
            ResultState.Error(e.message)
        }
    }
}