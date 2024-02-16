package com.android.pokeapp.ui.screens.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.android.pokeapp.sealeds.NavRoutes
import com.android.pokeapp.models.EventUI
import com.android.pokeapp.ui.screens.components.ErrorView
import com.android.pokeapp.ui.screens.components.LoadingView
import com.android.pokeapp.utils.encodeUrl

@Composable
fun PokemonListView(navController: NavHostController, viewModel: PokemonViewModel) {

    val state = viewModel.uiStateFlowFetchPokemonList.collectAsState()

    when(val value = state.value){
        is EventUI.OnError -> {
            ErrorView(value.message ){
                viewModel.getPokemonList()
            }
        }
        is EventUI.OnLoading -> {
            LoadingView()
        }
        is EventUI.OnSuccess -> {
            SuccessListView( value.data, viewModel.isLoading, { item ->
                navController.navigate(route = NavRoutes.Details.route + "/${item.url.encodeUrl()}")
            }, {
                viewModel.offset = viewModel.offset + viewModel.limit
                viewModel.getPokemonList()
            })
        }
    }

}