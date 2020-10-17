package com.example.mvvmstarterapp.ui.main.user

import android.os.Bundle
import android.view.View
import com.example.mvvmstarterapp.R
import com.example.mvvmstarterapp.core.BaseFragment
import com.example.mvvmstarterapp.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentUser : BaseFragment<FragmentUserViewModel, FragmentUserBinding>(
    FragmentUserViewModel::class.java, R.layout.fragment_user
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateExample()
    }

    override fun initBindingVariables() {
        binding.viewmodel = viewModel
    }

}