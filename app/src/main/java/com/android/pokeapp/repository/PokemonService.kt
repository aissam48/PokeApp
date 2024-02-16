package com.android.pokeapp.repository

import com.android.pokeapp.models.PokemonDetails
import com.android.pokeapp.repository.ApiResult
import kotlinx.coroutines.flow.Flow

interface PokemonService {
    suspend fun getPokemonList(params: HashMap<String, Any>): Flow<ApiResult<List<PokemonDetails>>>
    suspend fun getPokemonDetails( pokemonUrl: String): Flow<ApiResult<PokemonDetails>>
}
