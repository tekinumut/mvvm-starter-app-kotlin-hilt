package com.example.mvvmstarterapp.di

import com.example.mvvmstarterapp.BuildConfig
import com.example.mvvmstarterapp.data.service.MainApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApiService {
        return retrofit.create(MainApiService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val loggingBody = HttpLoggingInterceptor()
        loggingBody.setLevel(HttpLoggingInterceptor.Level.BODY)

        val clientBuilder = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            addInterceptor(getHeaders())
            //if (BuildConfig.DEBUG)
                addInterceptor(loggingBody)
        }

        return clientBuilder.build()
    }

    private fun getHeaders() = Interceptor { chain ->
        val newRequest = chain.request().newBuilder().apply {
            header("Content-Type", "application/json")
        }
        chain.proceed(newRequest.build())
    }

}