package com.example.mvvmstarterapp.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.mvvmstarterapp.base.BaseViewModel
import com.example.mvvmstarterapp.base.extension.Inflate

abstract class BaseFragmentVB<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VM>() {

    protected abstract val inflate: Inflate<VB>

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}