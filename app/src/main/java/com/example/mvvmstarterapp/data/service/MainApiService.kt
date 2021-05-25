package com.example.mvvmstarterapp.data.service

import com.example.mvvmstarterapp.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApiService {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserModel>
}

