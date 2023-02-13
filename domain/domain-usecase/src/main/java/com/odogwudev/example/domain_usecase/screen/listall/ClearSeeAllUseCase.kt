package com.odogwudev.example.domain_usecase.screen.listall

import com.odogwudev.example.movie_api.MovieService

class ClearSeeAllUseCase(private val movieService: MovieService) {

    operator fun invoke() {
        movieService.clearPopularMovies()
    }
}