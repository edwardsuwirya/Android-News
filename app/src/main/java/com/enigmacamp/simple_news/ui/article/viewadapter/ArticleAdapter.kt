package com.enigmacamp.simple_news.ui.article.viewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.databinding.ArticleViewHolderBinding

class ArticleAdapter(
    private val articles: List<Article>, private val cellClickListener: ArticleCellClickListener
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ArticleViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArticleViewHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.apply {
            tvArticleTitle.text = article.title
            tvArticleAuthor.text = article.author
            tvArticleDescription.text = article.description
            btnViewMoreArticle.setOnClickListener {
                cellClickListener.onCellClickListener(article)
            }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}