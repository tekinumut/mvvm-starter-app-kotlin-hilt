package com.example.mvvmstarterapp.repository

import android.content.Context
import android.util.Log
import com.example.mvvmstarterapp.models.Resource
import com.example.mvvmstarterapp.models.User
import com.example.mvvmstarterapp.network.MainService
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val mainService: MainService,
    private val context:Context
) {

    private suspend inline fun <T> safeApiCall(
        crossinline response: suspend () -> T
    ): Resource<T> {
        return try {
            Log.e("aaa",
            "a : ${context.toString()}")
            Resource.Success(response.invoke())
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    suspend fun getUser(surveyID: Int): Resource<User> {
        return safeApiCall { mainService.getUser(surveyID) }
    }
}