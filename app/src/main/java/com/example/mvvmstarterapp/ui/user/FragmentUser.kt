package com.example.mvvmstarterapp.ui.user

import com.example.mvvmstarterapp.R
import com.example.mvvmstarterapp.base.BaseFragment
import com.example.mvvmstarterapp.data.enums.ApiStateEnum
import com.example.mvvmstarterapp.databinding.FragmentUserBinding
import com.example.mvvmstarterapp.util.Resource
import com.example.mvvmstarterapp.util.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentUser : BaseFragment<FragmentUserBinding, FragmentUserViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_user
    override val classTypeOfViewModel: Class<FragmentUserViewModel> =
        FragmentUserViewModel::class.java

    override fun init() {
        binding.viewModel = viewModel
    }

    override fun initStartRequest() {
        viewModel.startRequest(1)
    }

    override fun setupObservers() {
        viewModel.getUser.observe(this, {
            when (it) {
                Resource.Loading -> {
                    viewModel.updateServiceState(ApiStateEnum.LOADING)
                }
                is Resource.Error -> {
                    viewModel.updateServiceState(ApiStateEnum.ERROR)
                    binding.textView.text = it.errorMessage
                }
                is Resource.Success -> {
                    viewModel.updateServiceState(ApiStateEnum.SUCCESS)
                    binding.textView.text = it.data.website + "\n\n  Click to Open WebSite"
                    binding.textView.setOnClickListener { view ->
                            Utility.openWebSiteWithCustomTabs(view.context, "https://github.com/tekinumut?tab=repositories")
                    }
                }
            }
        })
    }

}