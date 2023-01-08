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
import com.bumptech.glide.RequestManager
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.databinding.FragmentArticleBinding
import com.enigmacamp.simple_news.ui.article.viewadapter.ArticleAdapter
import com.enigmacamp.simple_news.ui.article.viewadapter.ArticleCellClickListener
import com.enigmacamp.simple_news.utils.Dialog
import com.enigmacamp.simple_news.utils.hideKeyboard
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

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var adapter: ArticleAdapter
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
        adapter = ArticleAdapter(requestManager, this)
        binding.apply {
            rvArticles.layoutManager = LinearLayoutManager(context)
            val dialog = Dialog(requireActivity())
            adapter.addLoadStateListener { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> dialog.show()
                    else -> {
                        dialog.dismiss()
                    }
                }
            }
            rvArticles.adapter = adapter

            ibSearchArticle.setOnClickListener {
                val keyword = binding.etArticle.text.toString()
                hideKeyboard()
                viewModel.getArticleBySource(safeArgs.sourceId, keyword, null)
                    .observe(viewLifecycleOwner) {
                        it?.let {
                            adapter.submitData(lifecycle, it)
                            adapter.notifyDataSetChanged()
                        }
                    }
            }
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
            viewModel.getArticleBySource(safeArgs.sourceId, null, null).observe(viewLifecycleOwner) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCellClickListener(data: Article, forView: Boolean) {
        val intent: Intent
        if (forView) {
            val uri = Uri.parse(data.url)
            intent = Intent(Intent.ACTION_VIEW, uri)
        } else {
            val i = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TITLE, data.title)
                putExtra(Intent.EXTRA_TEXT, data.url)
                type = "text/plain"
            }
            intent = Intent.createChooser(i, null)
        }
        startActivity(intent)


    }

    companion object {
        @JvmStatic
        fun newInstance() = ArticleFragment()
    }
}