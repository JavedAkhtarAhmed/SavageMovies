package com.javed.savagemovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.javed.savagemovies.R
import com.javed.savagemovies.adapters.MovieRecyclerSearchAdapter
import com.javed.savagemovies.databinding.FragmentSearchBinding
import com.javed.savagemovies.viewmodels.HomeActivityViewModel
import com.javed.savagemovies.viewmodels.HomeActivityViewModelFactory

class SearchFragment : Fragment() {
    lateinit var homeActivityViewModel: HomeActivityViewModel
    lateinit var searchFragmentBinding: FragmentSearchBinding
    lateinit var movieRecyclerSearchAdapter: MovieRecyclerSearchAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchFragmentBinding.homeActivityViewModel = homeActivityViewModel
        searchFragmentBinding.lifecycleOwner = this
        searchFragmentBinding.svSearchMovie.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                search(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return false
            }
        })

        movieRecyclerSearchAdapter = MovieRecyclerSearchAdapter()
        linearLayoutManager = LinearLayoutManager(context)
        searchFragmentBinding.rvSearchMovies.layoutManager = linearLayoutManager
        homeActivityViewModel.movieSearchMutableLiveData.observe(
            viewLifecycleOwner,
            Observer { item ->
                searchFragmentBinding.rvSearchMovies.adapter = movieRecyclerSearchAdapter
                movieRecyclerSearchAdapter.submitList(item)


            })


    }

    private fun search(newText: String?) {
        if (newText != null) {
            homeActivityViewModel.search(newText)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        val repository = (activity?.application as MovieApplication).movieRepository
        homeActivityViewModel = ViewModelProvider(
            this,
            HomeActivityViewModelFactory(repository)
        )[HomeActivityViewModel::class.java]
        return searchFragmentBinding.root
    }


}