package com.enigmacamp.simple_news.data.api

import com.enigmacamp.simple_news.data.api.interceptor.NewsApiKeyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitInstance {


    val retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(NewsApiKeyInterceptor())
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(NewsApi::class.java)
    }
}