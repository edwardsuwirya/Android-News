package com.enigmacamp.simple_news.ui.newssource

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmacamp.simple_news.data.api.response.Source
import com.enigmacamp.simple_news.databinding.ActivityNewsSourceBinding
import com.enigmacamp.simple_news.ui.article.ArticleActivity
import com.enigmacamp.simple_news.ui.newssource.viewadapter.NewsSourceCellClickListener
import com.enigmacamp.simple_news.ui.newssource.viewadapter.NewsSourceViewAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NewsSourceActivity : DaggerAppCompatActivity(), NewsSourceCellClickListener {
    @Inject
    lateinit var viewModel: NewsSourceViewModel
    private val adapter = NewsSourceViewAdapter(this)
    private lateinit var binding: ActivityNewsSourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSourceBinding.inflate(layoutInflater)
        binding.apply {
            rvSources.layoutManager = LinearLayoutManager(this@NewsSourceActivity)
            rvSources.adapter = adapter
        }
        setContentView(binding.root)
        initViewModel()
        subscribe()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModel as T
            }
        })[NewsSourceViewModel::class.java]
    }

    private fun subscribe() {
        intent.getStringExtra("category")?.let { source ->
            viewModel.sources.observe(this@NewsSourceActivity) {
                it?.let {
                    adapter.submitData(it)
                    adapter.notifyDataSetChanged()
                }
            }
            viewModel.getNewsSourceByCategory(source)
        }
    }

    override fun onCellClickListener(data: Source) {
        val i = Intent(this@NewsSourceActivity, ArticleActivity::class.java)
        i.putExtra("sourceId", data.id)
        startActivity(i)
    }
}