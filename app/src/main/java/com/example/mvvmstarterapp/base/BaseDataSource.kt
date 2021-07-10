package com.example.mvvmstarterapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mvvmstarterapp.utils.Resource
import retrofit2.Response

abstract class BaseDataSource {

    // All api requests go through here
    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.Success(body)
            }
            return Resource.Error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Resource.Error(e.message ?: e.toString())
        }
    }

    protected fun <T> observeApi(apiCall: suspend () -> Response<T>): LiveData<Resource<T>> =
        liveData {
            emit(Resource.Loading)
            emit(safeApiCall { apiCall.invoke() })
        }
}