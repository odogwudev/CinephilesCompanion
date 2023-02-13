package com.odogwudev.example.domain_usecase.helper.movie.upcoming

import com.odogwudev.example.movie_api.MovieService
import kotlinx.coroutines.flow.filterNotNull

class GetUpcomingMoviesFlowUseCase(private val movieService: MovieService) {

    operator fun invoke() = movieService.upcomingMovies.filterNotNull()
}