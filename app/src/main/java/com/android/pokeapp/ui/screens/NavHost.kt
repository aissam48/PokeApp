package com.android.pokeapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.pokeapp.sealeds.NavRoutes
import com.android.pokeapp.ui.screens.details.PokemonDetailsView
import com.android.pokeapp.ui.screens.list.PokemonListView


@Composable
fun NavigationHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {

        composable(NavRoutes.Home.route) {
            PokemonListView(navController, hiltViewModel())
        }

        composable(NavRoutes.Details.route + "/{url}") {
            val url = it.arguments?.getString("url")
            PokemonDetailsView(viewModel = hiltViewModel(), url = url ?: "")
        }

    }
}