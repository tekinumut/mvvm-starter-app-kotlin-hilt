package com.example.mvvmstarterapp.di

import com.example.mvvmstarterapp.di.main.MainFragmentBuildersModule
import com.example.mvvmstarterapp.di.main.MainModule
import com.example.mvvmstarterapp.di.main.MainViewModelModule
import com.example.mvvmstarterapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    /**
     * Buradaki her viewModel o activity'e bağlı olduğu için
     * AppComponent'te tanımlamıyoruz.
     */
    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            MainViewModelModule::class,
            MainFragmentBuildersModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}