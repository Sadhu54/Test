package com.test_app_21.network

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val value: T) : ResponseWrapper<T>()
    data class Error<out T>(val value: T, val code: T? = null) : ResponseWrapper<T>()
}
