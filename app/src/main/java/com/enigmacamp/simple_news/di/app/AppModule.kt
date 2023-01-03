package com.enigmacamp.simple_news.di.app

import com.enigmacamp.simple_news.data.api.NewsApi
import com.enigmacamp.simple_news.data.api.interceptor.NewsApiKeyInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(newsApiKeyInterceptor: NewsApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(newsApiKeyInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAuthApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }
}