package com.michael.data_source.service

import com.michael.data_source.model.EvolutionChain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EvolutionService {

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(@Path("id") name: Int): Response<EvolutionChain>
}