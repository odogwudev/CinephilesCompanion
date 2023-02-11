package com.odogwudev.example.explore_implementation

import com.odogwudev.example.movie.dto.MovieListResponse
import com.odogwudev.example.tv.dto.TvSeriesDiscoverResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExploreNetworkService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("region") region: List<String>,
        @Query("with_genres") withGenres: List<Int>,
        @Query("sort_by") sortBy: List<String>,
        @Query("page") page: Int
    ) : MovieListResponse

    @GET("discover/tv")
    suspend fun getTvSeries(
        @Query("region") region: List<String>,
        @Query("with_genres") withGenres: List<Int>,
        @Query("sort_by") sortBy: List<String>,
        @Query("page") page: Int
    ) : TvSeriesDiscoverResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ) : MovieListResponse

    @GET("search/tv")
    suspend fun searchTvSeries(
        @Query("query") query: String,
        @Query("page") page: Int
    ) : TvSeriesDiscoverResponse
}