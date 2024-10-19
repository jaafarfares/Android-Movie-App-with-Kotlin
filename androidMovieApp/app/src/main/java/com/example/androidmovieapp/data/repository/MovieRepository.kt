package com.example.androidmovieapp.data.repository

import com.example.androidmovieapp.data.api.ApiClient
import com.example.androidmovieapp.data.model.Movie
import com.example.androidmovieapp.data.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.androidmovieapp.BuildConfig
import com.example.androidmovieapp.data.model.MovieDetails

class MovieRepository {

    private val movieService = ApiClient.getService()

    fun discoverMovies(callback: (List<Movie>?) -> Unit) {
        val apiKey = BuildConfig.API_KEY
        println("*********** triggering api call ************")
        movieService.discoverMovies(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                println("response status code: ${response.code()}")
                if (response.isSuccessful) {
                    callback(response.body()?.results)
                } else {
                    println("Error fetching movies: ${response.message()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                println("api call failed: ${t.message}")
                callback(null)
            }
        })
    }

    // Function to get movie details
    fun getMovieDetails(movieId: Int, callback: (MovieDetails?) -> Unit) {
        val apiKey = BuildConfig.API_KEY // Access API key from BuildConfig
        println("Triggering getMovieDetails API call for movie ID: $movieId") // Debug statement
        movieService.getMovieDetails(movieId, apiKey).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                println("API call completed with status code: ${response.code()}") // Debug statement
                if (response.isSuccessful) {
                    callback(response.body()) // Return MovieDetails object
                } else {
                    println("Error fetching movie details: ${response.message()}") // Debug statement for errors
                    callback(null)
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                println("API call failed: ${t.message}") // Debug statement for failure
                callback(null)
            }
        })
    }
}
