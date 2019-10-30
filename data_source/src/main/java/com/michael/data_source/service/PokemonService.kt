package com.michael.data_source.service

import com.michael.data_source.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemons(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<NamedApiResourceList>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Response<Pokemon>

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Response<Pokemon>

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(@Path("name") name: String): Response<PokemonSpecies>

    @GET("ability/{name}")
    suspend fun getPokemonAbility(@Path("name") name: String): Response<Ability>
}