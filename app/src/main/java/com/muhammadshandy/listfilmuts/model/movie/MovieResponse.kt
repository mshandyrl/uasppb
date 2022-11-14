package com.muhammadshandy.listfilmuts.model.movie

import com.muhammadshandy.listfilmuts.model.movie.MovieModel

data class MovieResponse (
    val total_pages: Int,
    val results:List<MovieModel>,
)