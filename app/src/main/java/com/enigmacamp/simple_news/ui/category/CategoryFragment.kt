package com.enigmacamp.simple_news.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.enigmacamp.simple_news.databinding.FragmentCategoryBinding


/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnBusiness.setOnClickListener {
                val directions =
                    CategoryFragmentDirections.actionCategoryFragmentToNewsSourceFragment(category = "business")
                findNavController().navigate(directions)
            }
            btnEntertainment.setOnClickListener {
                val directions =
                    CategoryFragmentDirections.actionCategoryFragmentToNewsSourceFragment(category = "entertainment")
                findNavController().navigate(directions)
            }
            btnGeneral.setOnClickListener {
                val directions =
                    CategoryFragmentDirections.actionCategoryFragmentToNewsSourceFragment(category = "general")
                findNavController().navigate(directions)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoryFragment()
    }
}