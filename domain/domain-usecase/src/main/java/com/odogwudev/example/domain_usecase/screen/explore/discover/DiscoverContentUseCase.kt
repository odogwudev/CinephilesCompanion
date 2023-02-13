package com.odogwudev.example.domain_usecase.screen.explore.discover

import com.odogwudev.example.domain_usecase.screen.explore.Category
import com.odogwudev.example.domain_usecase.helper.movie.discover.DiscoverMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.tvSeries.discover.DiscoverTvSeriesUseCase
import com.odogwudev.example.domain_util.FilterType
import com.odogwudev.example.pagination_api.RefreshType

class DiscoverContentUseCase(
    private val discoverMoviesUseCase: DiscoverMoviesUseCase,
    private val discoverTvSeriesUseCase: DiscoverTvSeriesUseCase
) {

    suspend operator fun invoke(
        category: Category,
        region: List<String> = emptyList(),
        withGenres: List<Int> = emptyList(),
        sortBy: List<String> = listOf(FilterType.DEFAULT.value),
        refreshType: RefreshType
    ) = when (category) {
        Category.MOVIE -> discoverMoviesUseCase(region = region, withGenres = withGenres, sortBy = sortBy, refreshType = refreshType)
        Category.TV -> discoverTvSeriesUseCase(region = region, withGenres = withGenres, sortBy = sortBy, refreshType = refreshType)
    }
}