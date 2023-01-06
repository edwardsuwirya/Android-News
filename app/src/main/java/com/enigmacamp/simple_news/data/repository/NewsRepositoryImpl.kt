package com.enigmacamp.simple_news.data.repository

import com.enigmacamp.simple_news.data.api.NewsApi
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.data.api.response.Source
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) : NewsRepository {
    override fun getNewsBySourcePaging(
        source: String,
    ): LiveData<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false, initialLoadSize = 2),
            pagingSourceFactory = {
                ArticlePaging(source, newsApi)
            }, initialKey = 1
        ).liveData
    }

    override suspend fun getNewsSourceByCategory(
        category: String
    ): List<Source> {
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