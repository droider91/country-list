package com.ila.sample.di.core.module

import com.ila.data.BuildConfig
import com.ila.data.api.DemoAPI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by devendra on 15/05/2020
 **/
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): DemoAPI {
        return retrofit.create(DemoAPI::class.java)
    }
}