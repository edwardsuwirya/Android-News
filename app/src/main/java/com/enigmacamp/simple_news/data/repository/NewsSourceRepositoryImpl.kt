package com.enigmacamp.simple_news.data.repository

import com.enigmacamp.simple_news.data.api.NewsApi
import com.enigmacamp.simple_news.data.api.response.Source
import javax.inject.Inject

class NewsSourceRepositoryImpl @Inject constructor(private val newsApi: NewsApi) :NewsSourceRepository {
    override suspend fun getByCategory(category: String): List<Source> {
        try {
            val response =
                newsApi.getSourceByCategory(category)
            if (response.isSuccessful) {
                response.body().let {
                    return response.body()!!.sources
                }
            }
            return emptyList()
        } catch (e: Exception) {
            throw Exception("Server Error")
        }
    }
}