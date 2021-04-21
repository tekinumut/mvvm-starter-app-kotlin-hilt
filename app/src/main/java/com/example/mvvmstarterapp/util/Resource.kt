package com.example.mvvmstarterapp.util

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    class Success<out T : Any>(val data: T) : Resource<T>()
    class Error(@Suppress("unused") val errorMessage: String) : Resource<Nothing>()
}
