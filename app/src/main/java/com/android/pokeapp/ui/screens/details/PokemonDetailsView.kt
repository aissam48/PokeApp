package com.android.pokeapp.ui.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import com.android.pokeapp.models.EventUI
import com.android.pokeapp.ui.screens.components.ErrorView
import com.android.pokeapp.ui.screens.components.LoadingView
import com.android.pokeapp.utils.decodeUrl

@Composable
fun PokemonDetailsView(viewModel: PokemonDetailsViewModel, url:String){

    val state = viewModel.uiStateFlowFetchPokemonDetails.collectAsState()

    when (val value = state.value) {
        is EventUI.OnLoading -> {
            LoadingView()
        }

        is EventUI.OnSuccess -> {
            SuccessDetailsView(value.data)
        }

        is EventUI.OnError -> {
            ErrorView(value.message){
                viewModel.getPokemonDetails(url.decodeUrl())
            }
        }
    }

    DisposableEffect(Unit) {
        viewModel.getPokemonDetails(url.decodeUrl())
        onDispose {}
    }

}