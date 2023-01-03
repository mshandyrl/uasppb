package com.muhammadshandy.listfilmuts.uas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.muhammadshandy.listfilmuts.databinding.FragmentFavoriteMovieBinding
import com.muhammadshandy.listfilmuts.uas.data.model.viewparam.MovieViewParam
import com.muhammadshandy.listfilmuts.uas.utils.Resource
import com.muhammadshandy.listfilmuts.uas.utils.ViewModelFactory

class FavoriteMovieFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    /**
     * Variabel ini digunakan untuk membuat instance dari ViewModel
     */
    private val viewModel: MovieViewModel by viewModels(factoryProducer = {
        ViewModelFactory.getInstance(requireActivity().application)
    })

    /**
     * Variabel ini digunakan untuk membuat instance dari Adapter hanya jika dibutuhkan saja
     */
    private val adapter: ListMovieAdapter by lazy { ListMovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        setupRecyclerView()
    }

    /**
     * Fungsi ini sebagai wadah untuk data yang akan di-observe
     */
    private fun observeData() {
        // Ini digunakan untuk meng-observe (mengamati) perubahan state yang terjadi di view model
        viewModel.favoriteMovies.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> { // tampilkan loading
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> { // tampilkan data
                    adapter.submitList(resource.data)
                    adapter.setOnClickFavoriteListener { movie -> // Beri event klik pada toggle favorit
                        updateFavoriteMovie(movie)
                    }
                }
                is Resource.Error -> { // tampilkan error
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * Fungsi ini untuk setup recycler view
     */
    private fun setupRecyclerView() {
        binding.apply {
            rvMovies.adapter = adapter
            rvMovies.layoutManager = GridLayoutManager(
                requireContext(),
                2
            ) // Mengubah bentuk list menjadi grid dengan jumlah kolom 2
        }
    }

    private fun updateFavoriteMovie(movie: MovieViewParam) {
        if (movie.isFavorite) { // Cek jika status movie sudah ditambahkan ke favorit atau belum
            viewModel.updateFavorite(false, movie.id) // Jika sudah ubah statusnya menjadi false
        } else {
            viewModel.updateFavorite(true, movie.id) // Jika belum ubah statusnya menjadi true
        }
    }
}