package com.odogwudev.example.domain_usecase.helper.movie.trending

import com.odogwudev.example.movie_api.MovieService

class DeletePopularMoviesUseCase(private val movieService: MovieService) {

    operator fun invoke() = movieService.clearPopularMovies()
}