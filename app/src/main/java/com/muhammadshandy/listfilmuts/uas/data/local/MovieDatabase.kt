package com.muhammadshandy.listfilmuts.uas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.praktikum.uas.data.model.entity.MovieEntity

/**
 * Entities merupakan tempat inisialisasi entitas apa saja yang digunakan
 *
 * Setiap kali membuat kelas database, harus mencantumkan versi yang digunakan berupa integer (Untuk versi
 * yang digunakan bebas, biasanya dimulai dari 1)
 */
@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): MovieDatabase {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movie.db"
                    )
                        .build()
                }
            }
            return INSTANCE as MovieDatabase
        }
    }
}