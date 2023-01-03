package com.muhammadshandy.listfilmuts.uas.data.remote

import com.example.praktikum.uas.data.model.response.BaseResponse
import com.example.praktikum.uas.data.model.response.MovieResponse
import com.example.praktikum.uas.utils.Constants
import com.muhammadshandy.listfilmuts.uas.data.model.response.BaseResponse
import retrofit2.http.*

/**
 * Tempat endpoint yang akan digunakan
 */
interface ApiService {
    /**
     * Endpoint yang digunakan untuk mendapatkan list movie
     */
    @GET("movie/now_playing?api_key=${Constants.API_KEY}")
    suspend fun getMovies(): BaseResponse<MovieResponse>
}