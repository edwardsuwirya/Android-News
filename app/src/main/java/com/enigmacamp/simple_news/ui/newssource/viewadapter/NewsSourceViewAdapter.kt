package com.enigmacamp.simple_news.ui.newssource.viewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.simple_news.R
import com.enigmacamp.simple_news.data.api.response.Source
import com.enigmacamp.simple_news.databinding.ArticleViewHolderBinding
import com.enigmacamp.simple_news.databinding.SourcesViewHolderBinding

class NewsSourceViewAdapter(
    private val sources: List<Source>,
    private val cellClick: NewsSourceCellClickListener
) :
    RecyclerView.Adapter<NewsSourceViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: SourcesViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SourcesViewHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = sources[position]
        holder.binding.apply {
            tvSourceName.text = source.name
            tvSourceDescription.text = source.description
            btnViewArticle.setOnClickListener {
                cellClick.onCellClickListener(source)
            }
        }
    }

    override fun getItemCount(): Int {
        return sources.size
    }
}