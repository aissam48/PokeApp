package com.android.pokeapp.ui.screens.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pokeapp.R
import com.android.pokeapp.repository.ApiResult
import com.android.pokeapp.models.EventUI
import com.android.pokeapp.models.PokemonDetails
import com.android.pokeapp.repository.ApiServicesImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val apiServicesImpl: ApiServicesImpl,
    private val application: Application
) :
    ViewModel() {

    val uiStateFlowFetchPokemonDetails =
        MutableStateFlow<EventUI<PokemonDetails>>(EventUI.OnSuccess(PokemonDetails()))

    fun getPokemonDetails(url: String) {
        viewModelScope.launch {
            uiStateFlowFetchPokemonDetails.emit(EventUI.OnLoading())
            apiServicesImpl.getPokemonDetails(url).onEach { it ->
                when (it) {
                    is ApiResult.Success -> {
                        val data = it.data
                        if (data == null) {
                            uiStateFlowFetchPokemonDetails.emit(
                                EventUI.OnError(
                                    application.getString(
                                        R.string.error_message_at_server
                                    ),
                                )
                            )
                        } else {
                            uiStateFlowFetchPokemonDetails.emit(EventUI.OnSuccess(data))
                        }
                    }

                    is ApiResult.Error -> {
                        uiStateFlowFetchPokemonDetails.emit(
                            EventUI.OnError(
                                it.apiError.message,
                                it.apiError.statusCode
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }


}