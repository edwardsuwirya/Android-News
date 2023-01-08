package com.enigmacamp.simple_news.ui.newssource

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmacamp.simple_news.data.api.response.Source
import com.enigmacamp.simple_news.databinding.FragmentNewsSourceBinding
import com.enigmacamp.simple_news.ui.MainActivity
import com.enigmacamp.simple_news.ui.newssource.viewadapter.NewsSourceCellClickListener
import com.enigmacamp.simple_news.ui.newssource.viewadapter.NewsSourceViewAdapter
import com.enigmacamp.simple_news.utils.Dialog
import com.enigmacamp.simple_news.utils.ViewState
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [NewsSourceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsSourceFragment : DaggerFragment(), NewsSourceCellClickListener {
    @Inject
    lateinit var viewModel: NewsSourceViewModel
    private val adapter = NewsSourceViewAdapter(this)
    private lateinit var binding: FragmentNewsSourceBinding
    private val safeArgs: NewsSourceFragmentArgs by navArgs()
    private lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            safeArgs.category.uppercase()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsSourceBinding.inflate(layoutInflater, container, false)
        val activity = activity as? MainActivity
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.supportActionBar?.setHomeButtonEnabled(true)
        dialog = Dialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvSources.layoutManager = LinearLayoutManager(context)
            rvSources.adapter = adapter
        }
        Log.d("News-Source", "View created")
        initViewModel()
        subscribe()
        viewModel.getNewsSourceByCategory(safeArgs.category)
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModel as T
            }
        })[NewsSourceViewModel::class.java]
    }

    private fun subscribe() {
        viewModel.sources.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    is ViewState.Loading -> {
                        Log.d("News-Source", "Loading")
                        dialog.show()
                    }
                    is ViewState.Success -> {
                        it.data?.let { data ->
                            adapter.submitData(data)
                            adapter.notifyDataSetChanged()
                        }
                        Log.d("News-Source", "Success")
                        dialog.dismiss()
                    }
                    is ViewState.Error -> {
                        Log.d("News-Source", it.errorMessage.toString())
                        dialog.dismiss()
                    }
                }
            }
        }
    }

    override fun onCellClickListener(data: Source) {
        val directions =
            NewsSourceFragmentDirections.actionNewsSourceFragmentToArticleFragment(sourceId = data.id)
        findNavController().navigate(directions)
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsSourceFragment()
    }
}