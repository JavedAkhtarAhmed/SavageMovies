package com.javed.savagemovies.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javed.savagemovies.movierepository.MovieRepository

class MovieDetailActivityViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailActivityViewModel(movieRepository) as T
    }
}