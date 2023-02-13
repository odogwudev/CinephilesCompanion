package com.odogwudev.example.domain_usecase.helper.movie.search

import com.odogwudev.example.explore_api.ExploreService

class DeleteSearchMovieUseCase(private val exploreService: ExploreService) {

    operator fun invoke() = exploreService.clearSearchMovies()
}