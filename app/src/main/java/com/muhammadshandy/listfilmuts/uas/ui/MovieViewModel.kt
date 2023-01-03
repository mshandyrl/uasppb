package com.muhammadshandy.listfilmuts.uas.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadshandy.listfilmuts.uas.data.local.MovieDao
import com.muhammadshandy.listfilmuts.uas.data.local.MovieDatabase
import com.muhammadshandy.listfilmuts.uas.data.model.viewparam.MovieViewParam
import com.muhammadshandy.listfilmuts.uas.data.remote.ApiConfig
import com.muhammadshandy.listfilmuts.uas.utils.Resource
import com.muhammadshandy.listfilmuts.uas.utils.toMovieEntity
import com.muhammadshandy.listfilmuts.uas.utils.toMovieViewParam
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : ViewModel() {

    private var movieDao: MovieDao? = null

    private val _movies = MutableLiveData<Resource<List<MovieViewParam>>>()
    val movies: LiveData<Resource<List<MovieViewParam>>> = _movies

    private val _favoriteMovies = MutableLiveData<Resource<List<MovieViewParam>>>()
    val favoriteMovies: LiveData<Resource<List<MovieViewParam>>> = _favoriteMovies

    private val _updateFavorite = MutableLiveData<Resource<Unit>>()
    val updateFavorite: LiveData<Resource<Unit>> = _updateFavorite

    /**
     * Blok ini akan dieksekusi ketika instance ViewModel dibuat
     */
    init {
        // Membuat instance database
        val db = MovieDatabase.getInstance(application)
        movieDao = db.movieDao()

        // Mendapatkan list movie ketika instance View Model dibuat
        getAllMovies()

        // Mendapatkan list favorite movie ketika instance View Model dibuat
        getFavoriteMovies()
    }

    /**
     * Fungsi ini untuk mendapatkan list movie dari API
     */
    private fun getAllMovies() {
        // viewModelScope.launch ini untuk memanggil API di background agar tidak mengganggu UI Thread
        viewModelScope.launch {
            // inisialisasi nilai dari _movies menjadi loading
            // ini bertujuan untuk menampilkan loading ketika data sedang dimuat
            _movies.value = Resource.Loading()
            try {
                val movieResponses = ApiConfig.getApiService()
                    .getMovies().results // variabel ini untuk mendapatkan data list movie dari endpoint yang di-hit
                val movieEntities =
                    movieResponses.map { it.toMovieEntity() } // variabel ini untuk mengubah object response ke object entitas
                movieDao?.insertMovies(movieEntities) // simpan list movie dari API ke dalam database

                val result =
                    movieDao?.getMovies() // dapatkan data list movie yang telah disimpan ke database
                val data =
                    result?.map { it.toMovieViewParam() } // variabel ini untuk mengubah object entitas ke object yang akan digunakan oleh view
                data?.let {
                    _movies.value =
                        Resource.Success(data) // inisialisasi nilai _movies menjadi success ketika data berhasil didapatkan
                }
            } catch (e: Exception) {
                _movies.value =
                    Resource.Error(e.message.toString()) // inisialisasi nilai _movies menjadi error ketika data gagal didapatkan
            }
        }
    }

    /**
     * Fungsi ini untuk mendapatkan list favorite movie dari database
     */
    fun getFavoriteMovies() {
        viewModelScope.launch {
            // inisialisasi nilai dari _movies menjadi loading
            // ini bertujuan untuk menampilkan loading ketika data sedang dimuat
            _favoriteMovies.value = Resource.Loading()
            try {
                val result =
                    movieDao?.getFavoriteMovies() // variabel ini untuk mendapatkan data list movie dari database
                val data =
                    result?.map { it.toMovieViewParam() } // variabel ini untuk mengubah object entitas ke object yang akan digunakan oleh view
                data?.let {
                    _favoriteMovies.value =
                        Resource.Success(data) // inisialisasi nilai _favoriteMovies menjadi success ketika data berhasil didapatkan
                }
            } catch (e: Exception) {
                _favoriteMovies.value =
                    Resource.Error(e.message.toString()) // inisialisasi nilai _favoriteMovies menjadi error ketika data gagal didapatkan
            }
        }
    }

    /**
     * Fungsi ini untuk menambahkan/menghapus favorite movie
     */
    fun updateFavorite(isFavorite: Boolean, movieId: Int) {
        viewModelScope.launch {
            try {
                val result = movieDao?.updateFavorite(
                    isFavorite,
                    movieId
                ) // variabel ini untuk mengubah status favorite di database
                result?.let {
                    _updateFavorite.value =
                        Resource.Success(result) // inisialisasi nilai _updateFavorite menjadi success ketika data berhasil didapatkan
                }
            } catch (e: Exception) {
                _updateFavorite.value =
                    Resource.Error(e.message.toString()) // inisialisasi nilai _updateFavorite menjadi error ketika data gagal didapatkan
            }
        }
    }
}