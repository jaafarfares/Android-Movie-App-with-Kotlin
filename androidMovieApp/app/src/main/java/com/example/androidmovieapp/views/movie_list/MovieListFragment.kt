package com.example.androidmovieapp.views.movie_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmovieapp.R
import com.example.androidmovieapp.databinding.FragmentMovieListBinding
import com.example.androidmovieapp.viewmodels.MovieListViewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val movieListViewModel: MovieListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieListBinding.bind(view)

        val adapter = MovieListAdapter { movie ->
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movie.id!!)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        movieListViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        movieListViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            adapter.submitList(movies)
            binding.recyclerView.visibility = if (movies!!.isNotEmpty()) View.VISIBLE else View.GONE
        })

        movieListViewModel.fetchMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
