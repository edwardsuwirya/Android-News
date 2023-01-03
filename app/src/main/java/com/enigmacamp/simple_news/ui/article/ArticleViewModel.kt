package com.enigmacamp.simple_news.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private var _articles = MutableLiveData<List<Article>>()
    val sources: LiveData<List<Article>> get() = _articles

    fun getArticleBySource(sourceId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _articles.postValue(
                newsRepository.getNewsBySource(sourceId)
            )
        }
    }
}