package com.muhammadshandy.listfilmuts.uas.data.model.viewparam

/**
 * Kelas yang merepresentasikan object yang akan ditampilkan
 */
data class MovieViewParam(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
    val isFavorite: Boolean
)