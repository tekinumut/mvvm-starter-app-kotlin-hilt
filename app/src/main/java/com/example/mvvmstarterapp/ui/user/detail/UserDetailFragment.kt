package com.example.mvvmstarterapp.ui.user.detail

import androidx.navigation.fragment.navArgs
import com.example.mvvmstarterapp.base.extension.Inflate
import com.example.mvvmstarterapp.base.fragment.BaseFragmentVB
import com.example.mvvmstarterapp.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragmentVB<FragmentUserDetailBinding, UserDetailViewModel>() {

    override val inflate: Inflate<FragmentUserDetailBinding> = FragmentUserDetailBinding::inflate
    override val classTypeOfVM: Class<UserDetailViewModel> = UserDetailViewModel::class.java

    private val args by navArgs<UserDetailFragmentArgs>()

    override fun init() {
        super.init()
        binding.tvMessageFromHome.text = args.username ?: "No user found"
    }
}