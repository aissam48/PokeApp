package com.android.pokeapp.repository

import com.android.pokeapp.enums.EnumTags
import com.android.pokeapp.models.PokemonDetails
import com.android.pokeapp.utils.Constants.BASE_URL
import com.android.pokeapp.utils.JsonParser
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONArray
import javax.inject.Inject

class ApiServicesImpl @Inject constructor(private val apiManager: ApiManager): PokemonService {

    override suspend fun getPokemonList(params: HashMap<String, Any>): Flow<ApiResult<List<PokemonDetails>>> = flow {

        val parameters = arrayListOf<Pair<String, Any>>()
        for (i in params) {
            parameters.add(Pair(i.key, i.value))
        }

        apiManager.makeRequest(
            url = BASE_URL,
            reqMethod = HttpMethod.Get,
            parameterFormData = parameters,
            failureCallback = { error ->
                emit(ApiResult.Error(error.apiError))
            },
            successCallback = {
                var resultsArray = JSONArray()

                if (it.has(EnumTags.RESULTS.value)){
                    resultsArray = it.getJSONArray(EnumTags.RESULTS.value)
                }
                emit(ApiResult.Success(JsonParser.getPokemonList(resultsArray)))
            }
        )
    }

    override suspend fun getPokemonDetails(pokemonUrl: String): Flow<ApiResult<PokemonDetails>> = flow {

        apiManager.makeRequest(
            url = pokemonUrl,
            bodyMap = null,
            reqMethod = HttpMethod.Get,
            parameterFormData = null,
            failureCallback = { error ->
                emit(ApiResult.Error(error.apiError))
            },
            successCallback = {
                emit(ApiResult.Success(PokemonDetails(it)))
            }
        )
    }
}
