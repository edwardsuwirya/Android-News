package com.enigmacamp.simple_news.data.api

import com.enigmacamp.simple_news.data.api.response.NewsResponse
import com.enigmacamp.simple_news.data.api.response.NewsSourceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlineNews(
        @Query("sources") source: String
    ): Response<NewsResponse>

    @GET("sources")
    suspend fun getSourceByCategory(
        @Query("category") category: String
    ): Response<NewsSourceResponse>

}