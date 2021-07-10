package com.example.mvvmstarterapp.base.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmstarterapp.base.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {
    protected abstract val classTypeOfVM: Class<VM>

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(classTypeOfVM)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListeners()
        initObservers()
        // If the fragment is called from the stack, do not init again.
        if (!viewModel.isCreatedBefore) {
            initOnce()
            viewModel.isCreatedBefore = true
        }
    }

    open fun init() {}
    open fun initObservers() {}
    open fun initOnce() {}
    open fun initListeners() {}
}