package com.example.mvvmstarterapp.di

import android.app.Application
import android.content.Context
import com.example.mvvmstarterapp.network.MainService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    companion object {

        @Provides
        @Singleton
        fun provideApplication(app: Application): Context = app.applicationContext

        @Singleton
        @Provides
        fun provideRetrofitInstance(): MainService {
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MainService::class.java)
        }

    }
}