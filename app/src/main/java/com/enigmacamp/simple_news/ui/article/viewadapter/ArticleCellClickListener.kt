package com.enigmacamp.simple_news.ui.article.viewadapter

import com.enigmacamp.simple_news.data.api.response.Article

interface ArticleCellClickListener {
    fun onCellClickListener(data: Article, forView: Boolean)
}