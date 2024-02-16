package com.android.pokeapp.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pokeapp.repository.ApiResult
import com.android.pokeapp.models.EventUI
import com.android.pokeapp.models.PokemonItem
import com.android.pokeapp.models.PokemonDetails
import com.android.pokeapp.repository.ApiServicesImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: ApiServicesImpl,
) : ViewModel() {
    var offset = 0
    var limit = 20

    var isLoading = false

    private val listOfItems = mutableListOf<PokemonDetails>()

    val uiStateFlowFetchPokemonList =
        MutableStateFlow<EventUI<List<PokemonItem>>>(EventUI.OnSuccess(listOf()))

    init {
        getPokemonList()
    }

    fun getPokemonList() {

        viewModelScope.launch {

            val params = hashMapOf<String, Any>()
            params["offset"] = offset
            params["limit"] = limit
            isLoading = true
            if (offset == 0) {
                uiStateFlowFetchPokemonList.emit(EventUI.OnLoading())
            }
            repository.getPokemonList(params).onEach { it ->
                when (it) {
                    is ApiResult.Success -> {
                        if (offset > limit && it.data?.isEmpty() == true) {
                            offset -= limit
                        }

                        listOfItems.addAll(it.data ?: listOf())
                        val list  = listOfItems.map { PokemonItem(name = it.name, url = it.url) }
                        uiStateFlowFetchPokemonList.emit(EventUI.OnSuccess(list))
                    }

                    is ApiResult.Error -> {
                        uiStateFlowFetchPokemonList.emit(
                            EventUI.OnError(
                                it.apiError.message,
                                it.apiError.statusCode
                            )
                        )
                    }
                }
                isLoading = false
            }.launchIn(this)
        }
    }

}
