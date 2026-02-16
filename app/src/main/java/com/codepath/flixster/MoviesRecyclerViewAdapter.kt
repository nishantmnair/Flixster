package com.codepath.flixster

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesRecyclerViewAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = view.findViewById(R.id.movie_title)
        val movieDescription: TextView = view.findViewById(R.id.movie_description)
        val moviePoster: ImageView = view.findViewById(R.id.movie_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieTitle.text = movie.title
        holder.movieDescription.text = movie.overview

        val orientation = holder.itemView.resources.configuration.orientation
        val imageUrl = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            movie.backdropImageUrl
        } else {
            movie.posterImageUrl
        }

        Glide.with(holder.itemView)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .centerInside()
            .into(holder.moviePoster)
    }

    override fun getItemCount() = movies.size
}
