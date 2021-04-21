package com.example.mvvmstarterapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mvvmstarterapp.data.service.MainApiService
import com.example.mvvmstarterapp.util.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceRepository @Inject constructor(private val mainApi: MainApiService) {

    // All api requests go through here
    private suspend fun <T : Any> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) Resource.Error(NO_DATA)
                else Resource.Success(body)
            } else {
                Resource.Error(SERVER_ERROR)
            }
        } catch (e: Exception) {
            Log.e("ServiceRepository", e.message.toString())
            when (e) {
                is HttpException -> Resource.Error(SERVER_ERROR)
                is IOException -> Resource.Error(SERVER_ERROR)
                else -> Resource.Error(UNKNOWN_ERROR)
            }
        }
    }

    private fun <T> observeApi(networkCall: suspend () -> Resource<T>): LiveData<Resource<T>> =
        liveData {
            emit(Resource.Loading)
            emit(networkCall.invoke())
        }


    fun getUser(surveyID: Int) = observeApi { safeApiCall { mainApi.getUser(surveyID) } }

    companion object {
        const val SERVER_ERROR = "An error occurred while connecting to the server."
        const val NO_DATA = "No data to display."
        const val UNKNOWN_ERROR = "An unknown error has occurred."
    }

}