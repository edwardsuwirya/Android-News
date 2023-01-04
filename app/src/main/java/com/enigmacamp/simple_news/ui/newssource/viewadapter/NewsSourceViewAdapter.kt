package com.enigmacamp.simple_news.ui.newssource.viewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.simple_news.R
import com.enigmacamp.simple_news.data.api.response.Source

class NewsSourceViewAdapter(private val cellClick: NewsSourceCellClickListener) :
    RecyclerView.Adapter<NewsSourceViewHolder>() {
    private var sources: MutableList<Source> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sources_view_holder, parent, false)
        return NewsSourceViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int) {
        val source = sources[position]
        holder.textSourceName.text = source.name
        holder.textSourceDescription.text = source.description
        holder.buttonView.setOnClickListener {
            cellClick.onCellClickListener(source)
        }
    }

    override fun getItemCount(): Int {
        return sources.size
    }

    fun submitData(newSource: List<Source>) {
        sources.clear()
        sources.addAll(newSource)
    }
}