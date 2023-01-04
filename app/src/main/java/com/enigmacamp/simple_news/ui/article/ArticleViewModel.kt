package com.enigmacamp.simple_news.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.data.repository.NewsRepository
import javax.inject.Inject

class ArticleViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    fun getArticleBySource(sourceId: String): LiveData<PagingData<Article>> {
        return newsRepository.getNewsBySourcePaging(sourceId)
    }
}