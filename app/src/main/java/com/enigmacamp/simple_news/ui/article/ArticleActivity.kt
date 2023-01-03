package com.enigmacamp.simple_news.ui.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.databinding.ActivityArticleBinding
import com.enigmacamp.simple_news.ui.article.viewadapter.ArticleAdapter
import com.enigmacamp.simple_news.ui.article.viewadapter.ArticleCellClickListener
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ArticleActivity : DaggerAppCompatActivity(), ArticleCellClickListener {
    @Inject
    lateinit var viewModel: ArticleViewModel
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            rvArticles.layoutManager = LinearLayoutManager(this@ArticleActivity)
        }
        initViewModel()
        subscribe()
        intent.getStringExtra("sourceId")?.let {
            viewModel.getArticleBySource(it)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModel as T
            }
        })[ArticleViewModel::class.java]
    }

    private fun subscribe() {
        viewModel.sources.observe(this) {
            it?.let {
                val adapter =
                    ArticleAdapter(it, this@ArticleActivity)
                binding.rvArticles.adapter = adapter
            }
        }
    }

    override fun onCellClickListener(data: Article) {
        val uri = Uri.parse(data.url)
        val i = Intent(Intent.ACTION_VIEW, uri)
        startActivity(i)
    }
}