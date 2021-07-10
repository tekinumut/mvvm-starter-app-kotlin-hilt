package com.example.mvvmstarterapp.base.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.mvvmstarterapp.base.BaseViewModel

abstract class BaseDialogFragmentVB<DB : ViewDataBinding, VM : BaseViewModel> :
    BaseDialogFragment<VM>() {

    @get:LayoutRes
    protected abstract val layoutId: Int
    lateinit var binding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}