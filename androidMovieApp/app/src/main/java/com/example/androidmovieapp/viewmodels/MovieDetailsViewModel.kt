package com.example.androidmovieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidmovieapp.data.model.MovieDetails
import com.example.androidmovieapp.data.repository.MovieRepository

class MovieDetailsViewModel : ViewModel() {

    private val movieRepository = MovieRepository()

    private val _movieDetails = MutableLiveData<MovieDetails?>()
    val movieDetails: LiveData<MovieDetails?> get() = _movieDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchMovieDetails(movieId: Int) {
        _isLoading.value = true
        movieRepository.getMovieDetails(movieId) { movieDetails ->
            _isLoading.value = false
            if (movieDetails != null) {
                _movieDetails.value = movieDetails
            } else {
                _movieDetails.value = null
            }
        }
    }
}
