package com.android.pokeapp.sealeds

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Details : NavRoutes("details")
}
