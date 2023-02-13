package com.odogwudev.example.domain_usecase.helper.movie.discover

import com.odogwudev.example.domain_util.FilterType
import com.odogwudev.example.domain_util.result.wrapToResult
import com.odogwudev.example.explore_api.ExploreService
import com.odogwudev.example.pagination_api.RefreshType

class DiscoverMoviesUseCase(private val exploreService: ExploreService) {

    suspend operator fun invoke(
        region: List<String> = emptyList(),
        withGenres: List<Int> = emptyList(),
        sortBy: List<String> = listOf(FilterType.DEFAULT.value),
        refreshType: RefreshType
    ) = wrapToResult {
        exploreService.getMovies(
            region = region,
            withGenres = withGenres,
            sortBy = sortBy,
            refreshType = refreshType
        )
    }
}