package com.enigmacamp.simple_news.ui.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.databinding.FragmentArticleBinding
import com.enigmacamp.simple_news.ui.article.viewadapter.ArticleAdapter
import com.enigmacamp.simple_news.ui.article.viewadapter.ArticleCellClickListener
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : DaggerFragment(), ArticleCellClickListener {
    @Inject
    lateinit var viewModel: ArticleViewModel
    private val adapter = ArticleAdapter(this)
    private lateinit var binding: FragmentArticleBinding
    private val safeArgs: ArticleFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvArticles.layoutManager = LinearLayoutManager(context)
            adapter.addLoadStateListener { loadState ->
                when (loadState.append) {
                    is LoadState.Loading -> pbArticleLoading.visibility = View.VISIBLE
                    else -> {
                        pbArticleLoading.visibility = View.INVISIBLE
                    }
                }
            }
            rvArticles.adapter = adapter
        }
        initViewModel()
        subscribe()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModel as T
            }
        })[ArticleViewModel::class.java]
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.getArticleBySource(safeArgs.sourceId).observe(requireActivity()) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCellClickListener(data: Article) {
        val uri = Uri.parse(data.url)
        val i = Intent(Intent.ACTION_VIEW, uri)
        startActivity(i)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ArticleFragment()
    }
}