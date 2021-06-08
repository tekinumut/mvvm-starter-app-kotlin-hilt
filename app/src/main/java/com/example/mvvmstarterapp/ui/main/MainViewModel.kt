package com.example.mvvmstarterapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.mvvmstarterapp.base.BaseViewModel
import com.example.mvvmstarterapp.data.model.UserModel
import com.example.mvvmstarterapp.data.repository.UserRepository
import com.example.mvvmstarterapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: UserRepository) : BaseViewModel() {

    private val _userId = MutableLiveData<Int>()

    private val _getUser = _userId.switchMap { userId ->
        repository.getUser(userId)
    }

    val getUser: LiveData<Resource<UserModel>> = _getUser

    fun startRequest(userId: Int) {
        _userId.value = userId
    }

}