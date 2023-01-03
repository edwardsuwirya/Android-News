package com.enigmacamp.simple_news.ui.newssource.viewadapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.simple_news.R

class NewsSourceViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val textSourceName = v.findViewById(R.id.tv_source_name) as TextView
    val textSourceDescription = v.findViewById(R.id.tv_source_description) as TextView
    val buttonView = v.findViewById(R.id.btn_view_article) as Button
}