package com.example.mvvmstarterapp.ui.user

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.mvvmstarterapp.R
import com.example.mvvmstarterapp.base.fragment.BaseFragmentDB
import com.example.mvvmstarterapp.databinding.FragmentHomeBinding
import com.example.mvvmstarterapp.utils.Resource
import com.example.mvvmstarterapp.utils.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragmentDB<FragmentHomeBinding, HomeViewModel>() {

    override val layoutId: Int = R.layout.fragment_home
    override val classTypeOfVM: Class<HomeViewModel> = HomeViewModel::class.java

    override fun init() {
        super.init()
        binding.viewModel = viewModel
    }

    override fun initOnce() {
        super.initOnce()
        Log.d(TAG, "initOnce: triggered")
        viewModel.startRequest(1)
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.getUser.observe(this, { resource ->
            when (resource) {
                Resource.Loading -> Unit
                is Resource.Error -> {
                    binding.textView.text = resource.errorMessage
                }
                is Resource.Success -> {
                    binding.textView.text = resource.data.website
                    binding.navigateToDetail.setOnClickListener {
                        navigateToUserDetail(resource.data.username)
                    }
                }
            }
        })
    }

    override fun initListeners() {
        super.initListeners()
        binding.textView.setOnClickListener {
            Utility.openWebSiteWithCustomTabs(
                it.context,
                getString(R.string.my_github_repo_url)
            )
        }
    }

    private fun navigateToUserDetail(username: String?) {
        val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(username)
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "MyApp_HomeFragment"
    }
}