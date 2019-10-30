package com.michael.data_source.repository

import com.michael.data_source.model.*
import retrofit2.Response

interface PokemonRepository {
    suspend fun getPokemons(limit: Int, offset: Int): Response<NamedApiResourceList>
    suspend fun getPokemon(id: Int): Response<Pokemon>
    suspend fun getPokemon(name: String): Response<Pokemon>

    suspend fun getPokemonSpecies(name: String): Response<PokemonSpecies>

    suspend fun getPokemonAbility(name: String): Response<Ability>
}