package com.example.androidmovieapp.views.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidmovieapp.R
import com.example.androidmovieapp.data.model.MovieDetails
import com.example.androidmovieapp.databinding.FragmentMovieDetailsBinding
import com.example.androidmovieapp.utils.loadImage
import com.example.androidmovieapp.viewmodels.MovieDetailsViewModel

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
           // title = "Movie Details Screen"
            setDisplayHomeAsUpEnabled(true)
        }

        setHasOptionsMenu(true)

        val movieId = arguments?.getInt("movieId")
        binding.loadingIndicator.visibility = View.VISIBLE

        movieId?.let { movieDetailsViewModel.fetchMovieDetails(it) }

        movieDetailsViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.loadingIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner) { movieDetails ->
            if (movieDetails != null) {
                populateMovieDetails(movieDetails)
            } else {
                Toast.makeText(requireContext(), "Failed to load movie details", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun populateMovieDetails(movieDetails: MovieDetails) {
        binding.titleTextView.text = movieDetails.title
        binding.overviewTextView.text = movieDetails.overview
        binding.releaseDateTextView.text = movieDetails.release_date
        binding.voteAverageTextView.text = movieDetails.vote_average?.toString() ?: "N/A"

        val baseUrl = getString(R.string.image_base_url)
        binding.backdropImageView.loadImage("$baseUrl${movieDetails.backdrop_path}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
