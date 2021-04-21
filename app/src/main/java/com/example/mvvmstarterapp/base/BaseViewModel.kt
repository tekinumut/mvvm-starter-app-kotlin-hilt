package com.example.mvvmstarterapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmstarterapp.data.enums.ApiStateEnum

open class BaseViewModel : ViewModel(){
    private val _serviceState: MutableLiveData<ApiStateEnum?> = MutableLiveData()
    val serviceState: LiveData<ApiStateEnum?> = _serviceState

    fun updateServiceState(situation: ApiStateEnum?) {
        _serviceState.value = situation
    }
}