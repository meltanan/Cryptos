package com.example.cryptos.util

sealed class Resources<T>(val data: T?, val message: String? = null) {
    class Success<T>(data: T?): Resources<T>(data)
    class Error<T>(message: String, data: T? = null): Resources<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true): Resources<T>(null)
}
