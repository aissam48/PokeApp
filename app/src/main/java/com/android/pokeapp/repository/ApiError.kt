package com.android.pokeapp.repository


data class ApiError(
    var statusCode: Int = -1,
    var message: String = "",
)
