package com.javed.savagemovies.ui

import android.app.Application
import com.javed.savagemovies.api.RetrofitHelper
import com.javed.savagemovies.api.UserServices
import com.javed.savagemovies.movierepository.MovieRepository

class MovieApplication : Application() {
    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val movieService = RetrofitHelper.getInstance().create(UserServices::class.java)
        movieRepository = MovieRepository(movieService, applicationContext)
    }
}