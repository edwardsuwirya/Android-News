package com.enigmacamp.simple_news.data.repository

import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.data.api.response.Source

interface NewsRepository {
    suspend fun getNewsBySource(source: String): List<Article>?
    suspend fun getNewsSourceByCategory(category: String): List<Source>?
}