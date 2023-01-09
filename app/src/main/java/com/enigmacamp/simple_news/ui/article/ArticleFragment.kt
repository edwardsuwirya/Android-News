package com.enigmacamp.simple_news.ui.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.enigmacamp.simple_news.data.api.response.Article
import com.enigmacamp.simple_news.databinding.FragmentArticleBinding
import com.enigmacamp.simple_news.utils.Dialog
import com.enigmacamp.simple_news.utils.hideKeyboard
import com.enigmacamp.simple_news.utils.isNetworkOnline
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ArticleViewModel

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var adapter: ArticleAdapter
    private lateinit var binding: FragmentArticleBinding
    private val safeArgs: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ArticleAdapter(requestManager) { article, forView ->
            onCellClickListener(article, forView)
        }
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
                viewModel.keyword = keyword
                hideKeyboard()
                subscribe()
            }
        }
        initViewModel()
        subscribe()
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[ArticleViewModel::class.java]
    }

    private fun subscribe() {
        if (requireActivity().isNetworkOnline()) {
            binding.btnArticleRetry.visibility = View.GONE
            lifecycleScope.launch {
                viewModel.getArticleBySource(safeArgs.sourceId, viewModel.keyword, null)
                    .observe(viewLifecycleOwner) {
                        it?.let {
                            adapter.submitData(lifecycle, it)
                            adapter.notifyDataSetChanged()
                        }
                    }
            }
        } else {
            binding.apply {
                btnArticleRetry.visibility = View.VISIBLE
                btnArticleRetry.setOnClickListener {
                    subscribe()
                }
            }
            Snackbar.make(requireView(), "Offine", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun onCellClickListener(data: Article, forView: Boolean) {
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

    override fun onDetach() {
        super.onDetach()
        viewModel.keyword = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ArticleFragment()
    }
}