package com.example.mvvmstarterapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmstarterapp.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(
    private val mViewModelClass: Class<VM>,
    @LayoutRes private val layoutId: Int
) : DaggerFragment(layoutId) {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: VM
    lateinit var binding: DB

    private fun init(inflater: LayoutInflater, container: ViewGroup) {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, providerFactory).get(mViewModelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        init(inflater, container!!)
        initBindingVariables()
        return binding.root
    }

    abstract fun initBindingVariables()

}