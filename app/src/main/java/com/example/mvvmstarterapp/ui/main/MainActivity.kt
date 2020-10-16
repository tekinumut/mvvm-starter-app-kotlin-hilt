package com.example.mvvmstarterapp.ui.main

import android.os.Bundle
import com.example.mvvmstarterapp.R
import com.example.mvvmstarterapp.core.BaseActivity
import com.example.mvvmstarterapp.databinding.ActivityMainBinding
import com.example.mvvmstarterapp.models.Resource
import com.example.mvvmstarterapp.ui.main.user.FragmentUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getUser().observe(this, {
            when (it) {
                Resource.Loading -> viewModel.updateExample("Loading")
                is Resource.Success -> viewModel.updateExample("Success: ${it.data.username}")
                is Resource.Failure -> viewModel.updateExample("Error: ${it.error.message}")
            }
        })

        textView.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.containnerr, FragmentUser())
                .commit()
        }
    }

    override fun initBindingVariables(viewModel: MainViewModel) {
        binding.viewModel = viewModel
    }

}