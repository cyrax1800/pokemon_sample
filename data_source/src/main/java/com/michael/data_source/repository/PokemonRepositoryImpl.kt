package com.michael.data_source.repository

import com.michael.data_source.model.Ability
import com.michael.data_source.model.NamedApiResourceList
import com.michael.data_source.model.Pokemon
import com.michael.data_source.model.PokemonSpecies
import com.michael.data_source.service.PokemonService
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val service: PokemonService
) : PokemonRepository {

    override suspend fun getPokemons(limit: Int, offset: Int): Response<NamedApiResourceList> {
        return service.getPokemons(limit, offset)
    }

    override suspend fun getPokemon(id: Int): Response<Pokemon> {
        return service.getPokemon(id)
    }

    override suspend fun getPokemon(name: String): Response<Pokemon> {
        return service.getPokemon(name)
    }

    override suspend fun getPokemonSpecies(name: String): Response<PokemonSpecies> {
        return service.getPokemonSpecies(name)
    }

    override suspend fun getPokemonAbility(name: String): Response<Ability> {
        return service.getPokemonAbility(name)
    }
}