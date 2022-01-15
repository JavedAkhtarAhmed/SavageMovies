package com.javed.savagemovies.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javed.savagemovies.databinding.MovieListBinding
import com.javed.savagemovies.modelclasses.MoviesResponses
import com.javed.savagemovies.ui.MovieDetailActivity
import com.squareup.picasso.Picasso

class MovieRecyclerHomeAdapter() :
    ListAdapter<MoviesResponses, MovieRecyclerHomeAdapter.RecyclerAdapterViewHolder>(DiffUtil()) {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterViewHolder {
        val binding =
            MovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return RecyclerAdapterViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)


    }

    class RecyclerAdapterViewHolder(private val binding: MovieListBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(moviesResponses: MoviesResponses) {
            binding.apply {
                tvMovieDescription.text = moviesResponses.show.summary
                tvMovieTitle.text = moviesResponses.show.name
                Picasso.with(context).load(moviesResponses.show.image.medium).into(ivMovie)

                root.setOnClickListener {
                    val intent = Intent(context, MovieDetailActivity::class.java)
                    intent.putExtra("movie_id", moviesResponses.show.id)
                    it.context.startActivity(intent);
                }


            }

        }


    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<MoviesResponses>() {
        override fun areItemsTheSame(oldItem: MoviesResponses, newItem: MoviesResponses) =
            oldItem.show.id == newItem.show.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MoviesResponses, newItem: MoviesResponses) =
            oldItem == newItem
    }
}