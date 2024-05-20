package com.example.news.views.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.models.Article
import com.example.news.utilities.gone
import com.example.news.utilities.visible
import com.example.news.viewmodels.HomeViewModel
import com.example.news.views.adapters.NewsAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private val newsAdapter =NewsAdapter()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding.rvHome.adapter=newsAdapter

    }

    private fun observeData(){

        viewModel.items.observe(viewLifecycleOwner) { data: List<Article> ->

            newsAdapter.updateList(data)

        }

        viewModel.isLoading.observe(viewLifecycleOwner){isLoading:Boolean->

            if(isLoading){
                binding.progressBar.visible()
            }
            else{
               binding.progressBar.gone()

            }


        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}