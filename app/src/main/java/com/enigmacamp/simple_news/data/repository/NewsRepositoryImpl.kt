package com.enigmacamp.simple_news.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.enigmacamp.simple_news.data.api.RetrofitInstance
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.data.api.response.Source

class NewsRepositoryImpl : NewsRepository {
    override fun getNewsBySourcePaging(
        source: String,
    ): LiveData<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false, initialLoadSize = 2),
            pagingSourceFactory = {
                ArticlePaging(source, RetrofitInstance.retrofit)
            }, initialKey = 1
        ).liveData
    }

    override suspend fun getNewsSourceByCategory(
        category: String
    ): List<Source> {
        val response =
            RetrofitInstance.retrofit.getSourceByCategory(category)
        if (response.isSuccessful) {
            response.body()?.let {
                return response.body()!!.sources
            }
            return emptyList()
        }
        return emptyList()
    }
}