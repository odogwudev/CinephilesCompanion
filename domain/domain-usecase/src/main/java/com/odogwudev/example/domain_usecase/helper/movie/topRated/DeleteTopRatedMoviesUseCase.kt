package com.odogwudev.example.domain_usecase.helper.movie.topRated

import com.odogwudev.example.movie_api.MovieService

class DeleteTopRatedMoviesUseCase(private val movieService: MovieService) {

    operator fun invoke() = movieService.clearTopRatedMovies()
}