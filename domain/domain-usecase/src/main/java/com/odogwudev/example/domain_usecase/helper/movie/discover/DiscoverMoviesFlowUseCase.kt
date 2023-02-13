package com.odogwudev.example.domain_usecase.helper.movie.discover

import com.odogwudev.example.domain_model.ContentItem
import com.odogwudev.example.domain_model.toContentItem
import com.odogwudev.example.explore_api.ExploreService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class DiscoverMoviesFlowUseCase(private val exploreService: ExploreService) {

    operator fun invoke(): Flow<List<ContentItem>> =
        exploreService.discoverMovies.filterNotNull().map { list ->
            list.map { item -> item.toContentItem() }
        }
}