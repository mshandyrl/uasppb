package com.muhammadshandy.listfilmuts.uas.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muhammadshandy.listfilmuts.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    com.muhammadshandy.listfilmuts.uas.ui
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}