package com.enigmacamp.simple_news.ui.newssource

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.simple_news.R
import com.enigmacamp.simple_news.data.api.response.Source
import com.enigmacamp.simple_news.data.repository.NewsRepository
import com.enigmacamp.simple_news.data.repository.NewsRepositoryImpl
import com.enigmacamp.simple_news.ui.article.ArticleActivity
import com.enigmacamp.simple_news.ui.newssource.viewadapter.NewsSourceCellClickListener
import com.enigmacamp.simple_news.ui.newssource.viewadapter.NewsSourceViewAdapter

class NewsSourceActivity : AppCompatActivity(), NewsSourceCellClickListener {
    private lateinit var newsRepository: NewsRepository
    private lateinit var viewModel: NewsSourceViewModel
    private lateinit var rvSources: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_source)
        rvSources = findViewById(R.id.rv_sources)
        rvSources.layoutManager = LinearLayoutManager(this)
        newsRepository = NewsRepositoryImpl()
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NewsSourceViewModel(newsRepository) as T
            }
        })[NewsSourceViewModel::class.java]
        subscribe()
        intent.getStringExtra("category")?.let {
            Log.d("Intent Data", it)
            viewModel.getNewsSourceByCategory(it)
        }
    }

    private fun subscribe() {
        viewModel.sources.observe(this) {
            it?.let {
                val adapter =
                    NewsSourceViewAdapter(it, this@NewsSourceActivity)
                rvSources.adapter = adapter
            }
        }
    }

    override fun onCellClickListener(data: Source) {
        val i = Intent(this@NewsSourceActivity, ArticleActivity::class.java)
        i.putExtra("sourceId", data.id)
        startActivity(i)
    }
}