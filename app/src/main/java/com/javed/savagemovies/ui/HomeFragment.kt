package com.javed.savagemovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.javed.savagemovies.R
import com.javed.savagemovies.adapters.MovieRecyclerHomeAdapter
import com.javed.savagemovies.databinding.FragmentHomeBinding
import com.javed.savagemovies.interfaces.HomeFragToActivityInterface
import com.javed.savagemovies.viewmodels.HomeActivityViewModel
import com.javed.savagemovies.viewmodels.HomeActivityViewModelFactory


class HomeFragment : Fragment() {
    lateinit var homeFragmentBinding: FragmentHomeBinding
    lateinit var homeActivityViewModel: HomeActivityViewModel
    lateinit var movieRecyclerHomeAdapter: MovieRecyclerHomeAdapter
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private var mCallback: HomeFragToActivityInterface? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeFragmentBinding.homeActivityViewModel = homeActivityViewModel
        homeFragmentBinding.lifecycleOwner = this
        movieRecyclerHomeAdapter = MovieRecyclerHomeAdapter()
        staggeredGridLayoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        homeFragmentBinding.rvMovies.layoutManager = staggeredGridLayoutManager

        homeActivityViewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            homeFragmentBinding.rvMovies.adapter = movieRecyclerHomeAdapter
            movieRecyclerHomeAdapter.submitList(it)
        })
        homeFragmentBinding.svHome.setOnClickListener {
            mCallback?.onCLickSearchBar()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val repository = (activity?.application as MovieApplication).movieRepository
        homeActivityViewModel = ViewModelProvider(
            this,
            HomeActivityViewModelFactory(repository)
        )[HomeActivityViewModel::class.java]
        mCallback = activity as? HomeFragToActivityInterface

        return homeFragmentBinding.root
    }

}