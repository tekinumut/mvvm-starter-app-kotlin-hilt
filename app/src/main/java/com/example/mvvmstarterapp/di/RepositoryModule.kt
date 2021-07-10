package com.example.mvvmstarterapp.di

import com.example.mvvmstarterapp.data.repository.UserRepository
import com.example.mvvmstarterapp.data.service.MainApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepo(
        mainApiService: MainApiService
    ): UserRepository {
        return UserRepository(mainApiService)
    }
}