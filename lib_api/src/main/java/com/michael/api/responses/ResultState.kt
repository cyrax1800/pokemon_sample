package com.michael.api.responses

sealed class ResultState<out T : Any> {
    object Loading: ResultState<Nothing>()
    data class Success<out T : Any>(val data: T) : ResultState<T>()
    data class Error(val error: String?) : ResultState<Nothing>()
}