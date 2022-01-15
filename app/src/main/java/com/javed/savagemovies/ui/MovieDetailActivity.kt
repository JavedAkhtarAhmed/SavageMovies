package com.javed.savagemovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.javed.savagemovies.R
import com.javed.savagemovies.databinding.ActivityMovieDetailBinding
import com.javed.savagemovies.viewmodels.MovieDetailActivityViewModel
import com.javed.savagemovies.viewmodels.MovieDetailActivityViewModelFactory
import com.squareup.picasso.Picasso

class MovieDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieDetailBinding
    lateinit var movieDetailActivityViewModel: MovieDetailActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        binding.lifecycleOwner = this

        val repository = (application as MovieApplication).movieRepository
        movieDetailActivityViewModel = ViewModelProvider(
            this,
            MovieDetailActivityViewModelFactory(repository)
        )[MovieDetailActivityViewModel::class.java]

        val movieId = Integer.parseInt(
            intent.extras?.get("movie_id").toString()
        )

        movieDetailActivityViewModel.movieLiveData.observe(this, Observer {

            it.onEach {
                if (it.show.id == movieId.toLong()) {
                    Picasso.with(this).load(it.show.image.original).into(binding.ivMovieBig)
                    binding.tvMovieName.text = it.show.name
                    binding.tvMovieSummary.text = it.show.summary
                    binding.tvLanguage.text = it.show.language.toString()
                    binding.tvImdbRating.text = it.show.rating.average.toString()
                }
            }

        })

    }
}