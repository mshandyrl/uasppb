package com.muhammadshandy.listfilmuts.uas.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Kelas yang merepresentasikan object dari sebuah response
 */
data class MovieResponse(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
)