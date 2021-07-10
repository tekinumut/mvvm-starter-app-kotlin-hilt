package com.example.mvvmstarterapp.data.repository

import com.example.mvvmstarterapp.base.BaseDataSource
import com.example.mvvmstarterapp.data.service.MainApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val mainApi: MainApiService
) : BaseDataSource() {

    fun getUser(surveyID: Int) = observeApi { mainApi.getUser(surveyID) }
}