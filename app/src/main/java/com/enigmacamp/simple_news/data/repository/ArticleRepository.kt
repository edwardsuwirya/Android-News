package com.enigmacamp.simple_news.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.enigmacamp.simple_news.data.api.response.Article

interface ArticleRepository {
    fun getBySourcePaging(source: String): LiveData<PagingData<Article>>
}