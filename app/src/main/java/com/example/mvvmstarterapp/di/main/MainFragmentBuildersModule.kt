package com.example.mvvmstarterapp.di.main

import com.example.mvvmstarterapp.ui.main.user.FragmentUser
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): FragmentUser
}