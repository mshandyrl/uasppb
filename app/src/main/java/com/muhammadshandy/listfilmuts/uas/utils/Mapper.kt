package com.muhammadshandy.listfilmuts.uas.utils

import com.muhammadshandy.listfilmuts.uas.data.model.entity.MovieEntity
import com.muhammadshandy.listfilmuts.uas.data.model.response.MovieResponse
import com.muhammadshandy.listfilmuts.uas.data.model.viewparam.MovieViewParam

/**
 * Fungsi ini untuk mengubah object view param menjadi sebuah entitas untuk disimpan ke dalam database
 */
fun MovieViewParam.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        overview = overview,
        voteAverage = voteAverage,
    )
}

/**
 * Fungsi ini untuk mengubah object response untuk ditampilkan ke view
 */
fun MovieResponse.toMovieViewParam(): MovieViewParam {
    return MovieViewParam(
        id = id,
        title = title.orEmpty(),
        posterPath = posterPath.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        overview = overview.orEmpty(),
        voteAverage = voteAverage,
        isFavorite = false
    )
}

/**
 * Fungsi ini untuk mengubah object response ke object entitas
 */
fun MovieResponse.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title.orEmpty(),
        posterPath = posterPath.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        overview = overview.orEmpty(),
        voteAverage = voteAverage
    )
}

/**
 * Fungsi ini untuk mengubah object response untuk ditampilkan ke view
 */
fun MovieEntity.toMovieViewParam(): MovieViewParam {
    return MovieViewParam(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        overview = overview,
        voteAverage = voteAverage,
        isFavorite = isFavorite
    )
}