package com.javed.savagemovies.movierepository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.javed.savagemovies.api.UserServices
import com.javed.savagemovies.modelclasses.MoviesResponses

class MovieRepository(
    private val userServices: UserServices,
    private val applicationContext: Context
) {

    private val movieMutableLiveData = MutableLiveData<MutableList<MoviesResponses>>()

    val movieLiveData: LiveData<MutableList<MoviesResponses>>
        get() = movieMutableLiveData

    private val movieSearchMutableLiveData = MutableLiveData<MutableList<MoviesResponses>>()

    val movieSearchLiveData: LiveData<MutableList<MoviesResponses>>
        get() = movieSearchMutableLiveData

    suspend fun getMovies() {
        val result = userServices.movie()
        if (result?.body() != null) {
            movieMutableLiveData.postValue(result.body())
        }
    }

    suspend fun getMovieSearch(searchTerm: String) {
        val result = userServices.movieSearch(searchTerm)
        if (result?.body() != null) {
            movieSearchMutableLiveData.postValue(result.body())
        }
    }
}