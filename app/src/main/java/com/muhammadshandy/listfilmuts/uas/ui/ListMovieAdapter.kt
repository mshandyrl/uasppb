package com.muhammadshandy.listfilmuts.uas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhammadshandy.listfilmuts.databinding.ItemMoviesBinding
import com.muhammadshandy.listfilmuts.uas.data.model.viewparam.MovieViewParam
import com.muhammadshandy.listfilmuts.uas.utils.Constants

class ListMovieAdapter : RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {

    private val movies = arrayListOf<MovieViewParam>()

    private var callback: ((movie: MovieViewParam) -> Unit)? = null
    private var addToFavorite: ((movie: MovieViewParam) -> Unit)? = null
    private var deleteFromFavorite: ((movie: MovieViewParam) -> Unit)? = null

    private var isFavorite: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        with(holder.binding) {
            tvTitle.text = movie.title
            ratingBar.rating = movie.voteAverage.toFloat() / 2
            toggleFavorite.isChecked = movie.isFavorite
            Glide.with(holder.itemView.context)
                .load(Constants.IMG_URL + movie.posterPath)
                .into(ivPoster)

            toggleFavorite.setOnClickListener {
                callback?.invoke(movie)
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    fun submitList(movies: List<MovieViewParam>?) {
        movies?.let {
            this.movies.clear()
            this.movies.addAll(movies)
            notifyDataSetChanged()
        }
    }

    fun setOnClickFavoriteListener(callback: ((movie: MovieViewParam) -> Unit)?) {
        this.callback = callback
    }

    class ViewHolder(val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root)
}