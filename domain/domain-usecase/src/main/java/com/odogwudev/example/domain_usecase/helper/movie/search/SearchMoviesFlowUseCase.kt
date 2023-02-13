package com.odogwudev.example.domain_usecase.helper.movie.search

import com.odogwudev.example.domain_model.ContentItem
import com.odogwudev.example.domain_model.toContentItem
import com.odogwudev.example.explore_api.ExploreService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class SearchMoviesFlowUseCase(private val exploreService: ExploreService) {

    operator fun invoke(): Flow<List<ContentItem>> =
        exploreService.searchMovies.filterNotNull().filterNotNull().map { list ->
            list.map { item -> item.toContentItem() }
        }
}