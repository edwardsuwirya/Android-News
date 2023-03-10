package com.enigmacamp.simple_news.ui.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enigmacamp.simple_news.R
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.data.repository.NewsRepository
import com.enigmacamp.simple_news.data.repository.NewsRepositoryImpl
import com.enigmacamp.simple_news.ui.article.viewadapter.ArticleAdapter
import com.enigmacamp.simple_news.ui.article.viewadapter.ArticleCellClickListener
import kotlinx.coroutines.launch

class ArticleActivity : AppCompatActivity(), ArticleCellClickListener {
    private lateinit var newsRepository: NewsRepository
    private lateinit var viewModel: ArticleViewModel
    private lateinit var rvArticle: RecyclerView
    private val adapter = ArticleAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        rvArticle = findViewById(R.id.rv_articles)
        rvArticle.layoutManager = LinearLayoutManager(this)
        rvArticle.adapter = adapter
        newsRepository = NewsRepositoryImpl()
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ArticleViewModel(newsRepository) as T
            }
        })[ArticleViewModel::class.java]
        subscribe()
    }

    private fun subscribe() {
        intent.getStringExtra("sourceId")?.let { source ->
            lifecycleScope.launch {
                viewModel.getArticleBySource(source).observe(this@ArticleActivity) {
                    it?.let {
                        adapter.submitData(lifecycle, it)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onCellClickListener(data: Article) {
        val uri = Uri.parse(data.url)
        val i = Intent(Intent.ACTION_VIEW, uri)
        startActivity(i)
    }
}