package com.example.mvvmstarterapp.base.dialogfragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmstarterapp.base.BaseViewModel

abstract class BaseDialogFragment<VM : BaseViewModel> : DialogFragment() {

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

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    open fun init() {}
    open fun initObservers() {}
    open fun initOnce() {}
    open fun initListeners() {}
}