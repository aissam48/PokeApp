package com.android.pokeapp.repository

sealed class ApiResult<T>() {
    class Success<T>(val data: T?) : ApiResult<T>()
    class Error<T>(val apiError: ApiError) : ApiResult<T>()
}
