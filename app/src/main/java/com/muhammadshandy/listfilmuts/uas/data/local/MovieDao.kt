package ccom.muhammadshandy.listfilmuts.uas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.praktikum.uas.data.model.entity.MovieEntity

/**
 * Kelas ini berfungsi sebagai tempat untuk mengeksekusi query yang akan digunakan
 *
 * Di sini query yang digunakan adalah CREATE, READ, DELETE
 */
@Dao
interface MovieDao {

    /**
     * Fungsi untuk insert list movie
     *
     * Membutuhkan parameter object list movie
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    /**
     * Fungsi untuk update status favorite movie
     *
     * Membutuhkan parameter movie id
     */
    @Query("UPDATE movies SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(isFavorite: Boolean, id: Int)

    /**
     * Fungsi untuk hapus favorite movie
     *
     * Membutuhkan parameter id movie
     */
    @Query("DELETE FROM movies WHERE id = :id")
    suspend fun deleteFavorite(id: Int)

    /**
     * Fungsi untuk mendapatkan list movie
     */
    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieEntity>

    /**
     * Fungsi untuk mendapatkan list favorite movie
     */
    @Query("SELECT * FROM movies WHERE is_favorite = 1")
    suspend fun getFavoriteMovies(): List<MovieEntity>
}