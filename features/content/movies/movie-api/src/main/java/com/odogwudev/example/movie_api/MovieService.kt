package com.odogwudev.example.movie_api

import com.odogwudev.example.pagination_api.PagerItem
import com.odogwudev.example.pagination_api.RefreshType
import kotlinx.coroutines.flow.Flow

interface MovieService {

    val popularMovies: Flow<List<PagerItem>>

    val upcomingMovies: Flow<List<PagerItem>>

    val topRatedMovies: Flow<List<PagerItem>>

    val nowPlayingMovies: Flow<List<PagerItem>>

    suspend fun getUpcomingMovies(refreshType: RefreshType): List<PagerItem>

    suspend fun getPopularMovies(refreshType: RefreshType): List<PagerItem>

    suspend fun getTopRatedMovies(refreshType: RefreshType): List<PagerItem>

    suspend fun getNowPlayingMovies(refreshType: RefreshType): List<PagerItem>

    fun clearPopularMovies()

    fun clearUpcomingMovies()

    fun clearTopRatedMovies()

    fun clearNowPlayingMovies()
}