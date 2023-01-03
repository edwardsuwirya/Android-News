package com.enigmacamp.simple_news.ui.newssource.viewadapter

import com.enigmacamp.simple_news.data.api.response.Source

interface NewsSourceCellClickListener {
    fun onCellClickListener(data: Source)
}