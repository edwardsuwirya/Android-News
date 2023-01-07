package com.enigmacamp.simple_news.ui.newssource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.simple_news.data.api.response.Source
import com.enigmacamp.simple_news.data.repository.NewsSourceRepository
import com.enigmacamp.simple_news.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourceViewModel @Inject constructor(private val newsSourceRepository: NewsSourceRepository ) :
    ViewModel() {
    private var _sources = MutableLiveData<ViewState<List<Source>>>()
    val sources: LiveData<ViewState<List<Source>>> get() = _sources

    fun getNewsSourceByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _sources.postValue(ViewState.Loading)
            try {
                val result = newsSourceRepository.getByCategory(category)
                if (result.isEmpty()) {
                    _sources.postValue(ViewState.Error("No data"))
                } else {
                    _sources.postValue(ViewState.Success(result))
                }
            } catch (e: Exception) {
                _sources.postValue(ViewState.Error(e.message))
            }

        }
    }
}