package com.example.listcomponent.network

sealed class Async<out T> {

    data object Loading: Async<Nothing>()

    data class Error(val errorCode: Int, val errorMessage: String? = ""): Async<Nothing>()

    data class Success<out T>(val data: T): Async<T>()

    data object None: Async<Nothing>()
}