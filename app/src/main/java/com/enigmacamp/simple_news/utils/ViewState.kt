package com.enigmacamp.simple_news.utils

sealed class ViewState<out T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T?) : ViewState<T>(data)
    object Loading : ViewState<Nothing>()
    class Error<T>(errorMessage: String?) : ViewState<T>(null, errorMessage)
}
