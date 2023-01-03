package com.enigmacamp.simple_news.ui.article.viewadapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.simple_news.R

class ArticleHolder(v: View) : RecyclerView.ViewHolder(v) {
    val textArticleTitle = v.findViewById(R.id.tv_article_title) as TextView
    val textArticleAuthor = v.findViewById(R.id.tv_article_author) as TextView
    val textArticleDescription = v.findViewById(R.id.tv_article_description) as TextView
    val buttonViewMore = v.findViewById(R.id.btn_view_more_article) as Button
}