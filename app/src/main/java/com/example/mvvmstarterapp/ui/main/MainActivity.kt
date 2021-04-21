package com.example.mvvmstarterapp.ui.main

import android.os.Bundle
import com.example.mvvmstarterapp.R
import com.example.mvvmstarterapp.base.BaseActivity
import com.example.mvvmstarterapp.data.enums.ApiStateEnum
import com.example.mvvmstarterapp.databinding.ActivityMainBinding
import com.example.mvvmstarterapp.ui.user.FragmentUser
import com.example.mvvmstarterapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_main
    override val classTypeOfViewModel: Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MvvmStarterApp)
        super.onCreate(savedInstanceState)
    }

    override fun init() {
        binding.viewModel = viewModel
        binding.textView.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.containnerr, FragmentUser())
                .commit()
        }

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
                    binding.textView.text = it.errorMessage
                }
                is Resource.Success -> {
                    viewModel.updateServiceState(ApiStateEnum.SUCCESS)
                    binding.textView.text = it.data.username + "\n\n Click to Open Fragment"
                }
            }
        })
    }

    override fun initStartRequest() {
        viewModel.startRequest(1)
    }
}