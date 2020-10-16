package com.example.mvvmstarterapp.models

// A generic class that contains data and status about loading this data.
sealed class Resource<out H> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val error: Throwable) : Resource<Nothing>()

}

