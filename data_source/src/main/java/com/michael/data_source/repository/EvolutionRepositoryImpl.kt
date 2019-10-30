package com.michael.data_source.repository

import com.michael.data_source.model.EvolutionChain
import com.michael.data_source.service.EvolutionService
import retrofit2.Response
import javax.inject.Inject

class EvolutionRepositoryImpl @Inject constructor(
    private val service: EvolutionService
) : EvolutionRepository {

    override suspend fun getPokemonEvolution(id: Int): Response<EvolutionChain> {
        return service.getEvolutionChain(id)
    }
}