package com.enigmacamp.simple_news.data.repository

import com.enigmacamp.simple_news.data.api.NewsApi
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.data.api.response.Source
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) : NewsRepository {
    override suspend fun getNewsBySource(source: String): List<Article>? {
        val response =
            newsApi.getTopHeadlineNews(source)
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
            newsApi.getSourceByCategory(category)
        if (response.isSuccessful) {
            response.body()?.let {
                return response.body()!!.sources
            }
            return null
        }
        return null
    }
}