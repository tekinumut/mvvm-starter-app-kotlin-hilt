package com.example.mvvmstarterapp.ui.main

import android.os.Bundle
import android.util.Log
import com.example.mvvmstarterapp.R
import com.example.mvvmstarterapp.base.BaseActivity
import com.example.mvvmstarterapp.data.enums.ApiStateEnum
import com.example.mvvmstarterapp.databinding.ActivityMainBinding
import com.example.mvvmstarterapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutId: Int = R.layout.activity_main
    override val classTypeOfVM: Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun init() {
        binding.viewModel = viewModel
    }

    override fun setupObservers() {
        getUser()
    }

    private fun getUser() {
        viewModel.getUser.observe(this, {
            when (it) {
                Resource.Loading -> {
                    viewModel.updateServiceState(ApiStateEnum.LOADING)
                }
                is Resource.Error -> {
                    viewModel.updateServiceState(ApiStateEnum.ERROR)
                    Log.d(TAG, "errorMessage: ${it.errorMessage}")
                }
                is Resource.Success -> {
                    viewModel.updateServiceState(ApiStateEnum.SUCCESS)
                    Log.d(TAG, "username: ${it.data.username}")
                }
            }
        })
    }

    override fun initStartRequest() {
       // viewModel.startRequest(1)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}