package com.example.mvvmstarterapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    @get:LayoutRes
    protected abstract val layoutId: Int
    protected abstract val classTypeOfVM: Class<VM>
    lateinit var viewModel: VM
    lateinit var binding: DB
    private var isViewCreatedFirstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(classTypeOfVM)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupObservers()
        initLocal()
        // If the fragment is called from the stack, dont init again.
        if (isViewCreatedFirstTime) {
            initStartRequest()
            isViewCreatedFirstTime = false
        }
    }

    open fun init() {}
    open fun setupObservers() {}
    open fun initStartRequest() {}

    private fun initLocal() {
    }
}