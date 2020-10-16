package com.example.mvvmstarterapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.mvvmstarterapp.core.BaseViewModel
import com.example.mvvmstarterapp.models.Resource
import com.example.mvvmstarterapp.models.User
import com.example.mvvmstarterapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val repository: Repository
) : BaseViewModel() {

    fun getUser(): LiveData<Resource<User>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        val user = repository.getUser(1)
        emit(user)
    }

    private val _example: MutableLiveData<String> = MutableLiveData<String>()
    val example: LiveData<String> = _example

    fun updateExample(text:String){
        _example.value = text
    }
}