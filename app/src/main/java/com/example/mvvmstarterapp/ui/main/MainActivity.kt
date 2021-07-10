package com.example.mvvmstarterapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mvvmstarterapp.R
import com.example.mvvmstarterapp.base.BaseActivity
import com.example.mvvmstarterapp.databinding.ActivityMainBinding
import com.example.mvvmstarterapp.utils.DataStoreManager
import com.example.mvvmstarterapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutId: Int = R.layout.activity_main
    override val classTypeOfVM: Class<MainViewModel> = MainViewModel::class.java

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun init() {
        super.init()
        binding.viewModel = viewModel

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.getUser.observe(this, {
            when (it) {
                Resource.Loading -> Unit // means do nothing
                is Resource.Error -> {
                    Log.d(TAG, "errorMessage: ${it.errorMessage}")
                }
                is Resource.Success -> {
                    Log.d(TAG, "username: ${it.data.username}")
                }
            }
        })
        dataStoreManager.exampleCounterFlow.asLiveData().observe(this, {
            Log.d(TAG, "you opened mainActivity $it times")
        })
    }

    override fun initOnce() {
        super.initOnce()
        Log.d(TAG, "initOnce: triggered")
        lifecycleScope.launch {
            dataStoreManager.incrementCounter()
        }
        // viewModel.startRequest(1)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        const val TAG = "MyApp_MainActivity"
    }
}