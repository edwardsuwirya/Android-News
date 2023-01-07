package com.enigmacamp.simple_news.ui.article.viewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.enigmacamp.simple_news.R
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.databinding.ArticleViewHolderBinding

class ArticleAdapter(
    private val requestManager: RequestManager,
    private val cellClickListener: ArticleCellClickListener
) :
    PagingDataAdapter<Article, ArticleAdapter.ViewHolder>(ArticleComparator) {

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
        val article = getItem(position)!!
        holder.binding.apply {
            tvArticleTitle.text = article.title
            tvArticleAuthor.text = article.author
            tvArticleDescription.text = article.description
            tvPublished.text = article.publishedAt
            if (article.urlToImage.isNotEmpty()) {
                requestManager
                    .load(article.urlToImage)
                    .placeholder(R.drawable.img)
                    .into(ivArticle)
            } else {
                requestManager.clear(ivArticle)
                ivArticle.setImageDrawable(null)
            }

            btnViewMoreArticle.setOnClickListener {
                cellClickListener.onCellClickListener(article, true)
            }

            btnShare.setOnClickListener {
                cellClickListener.onCellClickListener(article, false)
            }
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