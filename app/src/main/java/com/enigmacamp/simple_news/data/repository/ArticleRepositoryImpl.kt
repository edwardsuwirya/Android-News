package com.enigmacamp.simple_news.data.repository

import com.enigmacamp.simple_news.data.api.NewsApi
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.enigmacamp.simple_news.data.api.response.Article
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(private val newsApi: NewsApi) : ArticleRepository {
    override fun getBySourcePaging(
        source: String,
    ): LiveData<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false, initialLoadSize = 2),
            pagingSourceFactory = {
                ArticlePaging(source, newsApi)
            }, initialKey = 1
        ).liveData
    }
}