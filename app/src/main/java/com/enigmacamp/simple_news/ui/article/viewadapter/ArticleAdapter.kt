package com.enigmacamp.simple_news.ui.article.viewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.simple_news.R
import com.enigmacamp.simple_news.data.api.response.Article

class ArticleAdapter(
    private val articles: List<Article>, private val cellClickListener: ArticleCellClickListener
) :
    RecyclerView.Adapter<ArticleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_view_holder, parent, false)
        return ArticleHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = articles[position]
        holder.textArticleTitle.text = article.title
        holder.textArticleAuthor.text = article.author
        holder.textArticleDescription.text = article.description
        holder.buttonViewMore.setOnClickListener {
            cellClickListener.onCellClickListener(article)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}