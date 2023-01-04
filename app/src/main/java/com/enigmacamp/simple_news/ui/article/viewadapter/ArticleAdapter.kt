package com.enigmacamp.simple_news.ui.article.viewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.enigmacamp.simple_news.R
import com.enigmacamp.simple_news.data.api.response.Article

class ArticleAdapter(
    private val cellClickListener: ArticleCellClickListener
) :
    PagingDataAdapter<Article, ArticleHolder>(ArticleComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_view_holder, parent, false)
        return ArticleHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = getItem(position)!!
        holder.textArticleTitle.text = article.title
        holder.textArticleAuthor.text = article.author
        holder.textArticleDescription.text = article.description
        holder.buttonViewMore.setOnClickListener {
            cellClickListener.onCellClickListener(article)
        }
    }

    object ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}