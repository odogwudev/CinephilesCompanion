package com.odogwudev.example.domain_usecase.screen.explore.discover

import com.odogwudev.example.domain_usecase.screen.explore.Category
import com.odogwudev.example.domain_model.ContentItem
import com.odogwudev.example.domain_usecase.helper.movie.discover.DiscoverMoviesFlowUseCase
import com.odogwudev.example.domain_usecase.helper.tvSeries.discover.DiscoverTvSeriesFlowUseCase
import kotlinx.coroutines.flow.Flow

class DiscoverContentFlowUseCase(
    private val discoverMoviesFlowUseCase: DiscoverMoviesFlowUseCase,
    private val discoverTvSeriesFlowUseCase: DiscoverTvSeriesFlowUseCase
) {

    operator fun invoke(category: Category): Flow<List<ContentItem>> = when (category) {
        Category.MOVIE -> discoverMoviesFlowUseCase()
        Category.TV -> discoverTvSeriesFlowUseCase()
    }
}