package com.example.mvvmstarterapp.di.main

import androidx.lifecycle.ViewModel
import com.example.mvvmstarterapp.di.ViewModelKey
import com.example.mvvmstarterapp.ui.main.MainViewModel
import com.example.mvvmstarterapp.ui.main.user.FragmentUserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * MainActivity'e ait viewModel modulleri
 */
@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FragmentUserViewModel::class)
    abstract fun bindUerFragmentViewModel(fragmentUserViewModel: FragmentUserViewModel): ViewModel
}