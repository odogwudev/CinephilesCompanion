package com.odogwudev.example.domain_usecase.screen.explore.search

import com.odogwudev.example.domain_usecase.screen.explore.Category
import com.odogwudev.example.domain_usecase.helper.movie.search.SearchMoviesFlowUseCase
import com.odogwudev.example.domain_usecase.helper.tvSeries.search.SearchTvSeriesFlowUseCase

class SearchContentFlowUseCase(
    private val searchMoviesFlowUseCase: SearchMoviesFlowUseCase,
    private val searchTvSeriesFlowUseCase: SearchTvSeriesFlowUseCase
) {

    operator fun invoke(category: Category) = when (category) {
        Category.MOVIE -> searchMoviesFlowUseCase()
        Category.TV -> searchTvSeriesFlowUseCase()
    }
}