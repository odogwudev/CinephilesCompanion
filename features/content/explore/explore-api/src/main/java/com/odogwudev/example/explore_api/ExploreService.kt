package com.odogwudev.example.explore_api

import com.odogwudev.example.pagination_api.PagerItem
import com.odogwudev.example.pagination_api.RefreshType
import kotlinx.coroutines.flow.Flow

interface ExploreService {

    val discoverMovies: Flow<List<PagerItem>>

    val searchMovies: Flow<List<PagerItem>>

    val discoverTvSeries: Flow<List<PagerItem>>

    val searchTvSeries: Flow<List<PagerItem>>

    suspend fun getMovies(region: List<String>, withGenres: List<Int>, sortBy: List<String>, refreshType: RefreshType) : List<PagerItem>

    suspend fun getTvSeries(region: List<String>, withGenres: List<Int>, sortBy: List<String>, refreshType: RefreshType) : List<PagerItem>

    suspend fun searchMovies(query: String, refreshType: RefreshType) : List<PagerItem>

    suspend fun searchTvSeries(query: String, refreshType: RefreshType) : List<PagerItem>

    fun clearSearchMovies()

    fun clearSearchTvSeries()
}