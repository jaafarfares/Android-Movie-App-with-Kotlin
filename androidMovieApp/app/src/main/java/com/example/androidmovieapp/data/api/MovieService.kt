package com.example.androidmovieapp.data.api

import com.example.androidmovieapp.data.model.Movie
import com.example.androidmovieapp.data.model.MovieDetails
import com.example.androidmovieapp.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun discoverMovies(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetails>

}
