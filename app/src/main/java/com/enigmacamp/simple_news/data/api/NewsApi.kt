package com.enigmacamp.simple_news.data.api

import com.enigmacamp.simple_news.data.api.response.NewsResponse
import com.enigmacamp.simple_news.data.api.response.NewsSourceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getTopHeadlineNews(
        @Query("sources") source: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<NewsResponse>

    @GET("everything")
    suspend fun searchTopHeadlineNews(
        @Query("sources") source: String,
        @Query("q") keyword: String,
        @Query("searchIn") searchIn: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<NewsResponse>

    @GET("sources")
    suspend fun getSourceByCategory(
        @Query("category") category: String,
    ): Response<NewsSourceResponse>

}