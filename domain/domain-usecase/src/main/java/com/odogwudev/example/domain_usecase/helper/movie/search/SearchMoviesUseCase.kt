package com.odogwudev.example.domain_usecase.helper.movie.search

import com.odogwudev.example.domain_util.result.wrapToResult
import com.odogwudev.example.explore_api.ExploreService
import com.odogwudev.example.pagination_api.RefreshType

class SearchMoviesUseCase(private val exploreService: ExploreService) {

    suspend operator fun invoke(query: String, refreshType: RefreshType) = wrapToResult {
        exploreService.searchMovies(
            query = query,
            refreshType = refreshType
        )
    }
}