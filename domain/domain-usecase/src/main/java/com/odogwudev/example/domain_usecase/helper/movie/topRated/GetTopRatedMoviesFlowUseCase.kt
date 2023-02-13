package com.odogwudev.example.domain_usecase.helper.movie.topRated

import com.odogwudev.example.movie_api.MovieService
import kotlinx.coroutines.flow.filterNotNull

class GetTopRatedMoviesFlowUseCase(private val movieService: MovieService) {

    operator fun invoke() = movieService.topRatedMovies.filterNotNull()
}