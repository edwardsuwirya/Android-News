package com.enigmacamp.simple_news.data.repository

import com.enigmacamp.simple_news.data.api.response.Source

interface NewsSourceRepository {
    suspend fun getByCategory(category: String): List<Source>
}