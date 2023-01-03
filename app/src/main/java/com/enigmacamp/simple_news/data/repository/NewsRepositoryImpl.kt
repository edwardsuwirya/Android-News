package com.enigmacamp.simple_news.data.repository

import com.enigmacamp.simple_news.data.api.RetrofitInstance
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.data.api.response.Source

class NewsRepositoryImpl : NewsRepository {
    override suspend fun getNewsBySource(source: String): List<Article>? {
        val response =
            RetrofitInstance.retrofit.getTopHeadlineNews(source)
        if (response.isSuccessful) {
            response.body()?.let {
                return response.body()!!.articles
            }
            return null
        }
        return null
    }

    override suspend fun getNewsSourceByCategory(
        category: String
    ): List<Source>? {
        val response =
            RetrofitInstance.retrofit.getSourceByCategory(category)
        if (response.isSuccessful) {
            response.body()?.let {
                return response.body()!!.sources
            }
            return null
        }
        return null
    }
}