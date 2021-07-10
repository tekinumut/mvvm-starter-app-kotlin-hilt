package com.example.mvvmstarterapp.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.mvvmstarterapp.base.BaseViewModel
import com.example.mvvmstarterapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: UserRepository) :
    BaseViewModel() {

    private val _userId = MutableLiveData<Int>()

    val getUser = _userId.switchMap { userId ->
        repository.getUser(userId)
    }

    fun startRequest(userId: Int) {
        _userId.value = userId
    }
}