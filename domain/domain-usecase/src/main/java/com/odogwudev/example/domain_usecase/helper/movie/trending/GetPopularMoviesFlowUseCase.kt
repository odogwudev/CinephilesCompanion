package com.odogwudev.example.domain_usecase.helper.movie.trending

import com.odogwudev.example.movie_api.MovieService
import kotlinx.coroutines.flow.filterNotNull

class GetPopularMoviesFlowUseCase(private val movieService: MovieService) {

    operator fun invoke() = movieService.popularMovies.filterNotNull()
}