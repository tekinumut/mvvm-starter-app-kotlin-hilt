package com.example.mvvmstarterapp.ui.main.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmstarterapp.core.BaseViewModel
import com.example.mvvmstarterapp.repository.Repository
import javax.inject.Inject

class FragmentUserViewModel
@ViewModelInject constructor() : BaseViewModel() {

    private val _example: MutableLiveData<String> = MutableLiveData<String>()

    val example: LiveData<String> = _example

    fun updateExample() {
        _example.value = "Binding Example"
    }
}