package com.odogwudev.example.movie_implementation

import com.odogwudev.example.movie.dto.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieNetworkService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int) : MovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int) : MovieListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int) : MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int) : MovieListResponse
}