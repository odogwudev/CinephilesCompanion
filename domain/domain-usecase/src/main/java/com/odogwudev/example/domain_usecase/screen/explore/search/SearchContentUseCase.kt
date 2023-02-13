package com.odogwudev.example.domain_usecase.screen.explore.search

import com.odogwudev.example.domain_usecase.screen.explore.Category
import com.odogwudev.example.domain_usecase.helper.movie.search.SearchMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.tvSeries.search.SearchTvSeriesUseCase
import com.odogwudev.example.pagination_api.RefreshType

class SearchContentUseCase(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val searchTvSeriesUseCase: SearchTvSeriesUseCase
) {

    suspend operator fun invoke(category: Category, query: String, refreshType: RefreshType) = when (category) {
        Category.MOVIE -> searchMoviesUseCase(query = query, refreshType = refreshType)
        Category.TV -> searchTvSeriesUseCase(query = query, refreshType = refreshType)
    }
}