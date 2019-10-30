package com.michael.feature_pokemon_abilities.domain

import com.michael.api.responses.ResultState
import com.michael.data_source.model.Ability
import com.michael.data_source.repository.PokemonRepository
import javax.inject.Inject

class PokemonAbilityUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend fun getPokemonAbility(name: String): ResultState<Ability> {
        return try {
            val response = repository.getPokemonAbility(name)
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