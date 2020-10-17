package com.example.mvvmstarterapp.di

import android.content.Context
import com.example.mvvmstarterapp.network.MainService
import com.example.mvvmstarterapp.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideRepository(mainService: MainService,context: Context): Repository {
        return Repository(mainService,context)
    }
}