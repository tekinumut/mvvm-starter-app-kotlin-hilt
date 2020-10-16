package com.example.mvvmstarterapp.network

import com.example.mvvmstarterapp.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User
}

