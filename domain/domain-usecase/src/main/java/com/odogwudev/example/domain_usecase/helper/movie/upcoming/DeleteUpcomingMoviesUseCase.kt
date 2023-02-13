package com.odogwudev.example.domain_usecase.helper.movie.upcoming

import com.odogwudev.example.movie_api.MovieService

class DeleteUpcomingMoviesUseCase(private val movieService: MovieService) {

    operator fun invoke() = movieService.clearUpcomingMovies()
}