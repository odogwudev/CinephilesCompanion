package com.odogwudev.example.domain_usecase.helper.tvSeries.search

import com.odogwudev.example.explore_api.ExploreService

class DeleteSearchTvSeriesUseCase(private val exploreService: ExploreService) {

    operator fun invoke() = exploreService.clearSearchTvSeries()
}