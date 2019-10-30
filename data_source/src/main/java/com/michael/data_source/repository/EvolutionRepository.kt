package com.michael.data_source.repository

import com.michael.data_source.model.EvolutionChain
import retrofit2.Response

interface EvolutionRepository {
    suspend fun getPokemonEvolution(id: Int): Response<EvolutionChain>
}