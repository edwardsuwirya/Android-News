package com.enigmacamp.simple_news.di.modules

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.enigmacamp.simple_news.BuildConfig
import com.enigmacamp.simple_news.data.api.NewsApi
import com.enigmacamp.simple_news.data.api.interceptor.NewsApiKeyInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class CoreModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(newsApiKeyInterceptor: NewsApiKeyInterceptor): OkHttpClient {
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .addInterceptor(newsApiKeyInterceptor)
            .addInterceptor(httpLogging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAuthApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    fun provideGlide(application: Application): RequestManager {
        return Glide.with(application.applicationContext)
    }
}