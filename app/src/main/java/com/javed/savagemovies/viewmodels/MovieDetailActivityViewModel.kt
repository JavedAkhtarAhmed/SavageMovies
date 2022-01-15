package com.javed.savagemovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javed.savagemovies.modelclasses.MoviesResponses
import com.javed.savagemovies.movierepository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailActivityViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMovies()
        }
    }

    val movieLiveData: LiveData<MutableList<MoviesResponses>> = movieRepository.movieLiveData


}