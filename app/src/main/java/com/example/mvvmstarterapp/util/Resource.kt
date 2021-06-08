package com.example.mvvmstarterapp.util

sealed class Resource<out T>(val status: Status) {
    object Loading : Resource<Nothing>(Status.LOADING)
    class Success<out T : Any>(val data: T) : Resource<T>(Status.SUCCESS)
    class Error(val errorMessage: String) : Resource<Nothing>(Status.ERROR)

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}
