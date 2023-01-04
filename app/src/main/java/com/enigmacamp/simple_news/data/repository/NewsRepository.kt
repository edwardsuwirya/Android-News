package com.enigmacamp.simple_news.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.data.api.response.Source

interface NewsRepository {
    fun getNewsBySourcePaging(source: String): LiveData<PagingData<Article>>
    suspend fun getNewsSourceByCategory(category: String): List<Source>
}