package com.example.mvvmstarterapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutId: Int
    protected abstract val classTypeOfVM: Class<VM>
    lateinit var binding: DB
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(classTypeOfVM)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        init()
        setupObservers()
        initStartRequest()
        initLocal()
    }

    open fun init() {}
    open fun setupObservers() {}
    open fun initStartRequest() {}

    private fun initLocal() {

    }
}
