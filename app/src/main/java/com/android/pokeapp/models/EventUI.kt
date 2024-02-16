package com.android.pokeapp.models

sealed class EventUI<T> {
    class OnLoading<T> : EventUI<T>()
    class OnSuccess<T>(val data: T) : EventUI<T>()
    class OnError<T>(val message:String, val statusCode:Int = 500): EventUI<T>()
}
