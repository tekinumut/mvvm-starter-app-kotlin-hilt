package com.example.mvvmstarterapp.ui.user

import com.example.mvvmstarterapp.R
import com.example.mvvmstarterapp.base.BaseFragment
import com.example.mvvmstarterapp.databinding.FragmentHomeBinding
import com.example.mvvmstarterapp.util.Resource
import com.example.mvvmstarterapp.util.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    override val layoutId: Int = R.layout.fragment_home
    override val classTypeOfVM: Class<HomeFragmentViewModel> = HomeFragmentViewModel::class.java

    override fun init() {
        binding.viewModel = viewModel
    }

    override fun initStartRequest() {
        viewModel.startRequest(1)
    }

    override fun setupObservers() {
        viewModel.getUser.observe(this, {
            when (it) {
                is Resource.Error -> {
                    binding.textView.text = it.errorMessage
                }
                is Resource.Success -> {
                    binding.textView.text = it.data.website + "\n\n  Click to Open WebSite"
                    binding.textView.setOnClickListener { view ->
                        Utility.openWebSiteWithCustomTabs(
                            view.context,
                            getString(R.string.my_github_repo_url)
                        )
                    }
                }
                Resource.Loading -> {}
            }
        })
    }

}