package com.example.mvvmstarterapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.mvvmstarterapp.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}