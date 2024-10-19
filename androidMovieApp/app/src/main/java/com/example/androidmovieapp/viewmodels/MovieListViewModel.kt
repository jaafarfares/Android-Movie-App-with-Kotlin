package com.example.androidmovieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidmovieapp.data.model.Movie
import com.example.androidmovieapp.data.repository.MovieRepository

class MovieListViewModel : ViewModel() {

    private val movieRepository = MovieRepository()

    private val _movies = MutableLiveData<List<Movie>?>()
    val movies: LiveData<List<Movie>?> get() = _movies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchMovies() {
        _isLoading.value = true
        movieRepository.discoverMovies { movies ->
            _isLoading.value = false
            if (movies != null) {
                _movies.value = movies
            } else {
                _movies.value = emptyList()
            }
        }
    }
}
