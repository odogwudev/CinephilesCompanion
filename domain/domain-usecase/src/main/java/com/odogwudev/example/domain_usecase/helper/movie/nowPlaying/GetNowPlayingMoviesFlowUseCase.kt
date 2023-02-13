package com.odogwudev.example.domain_usecase.helper.movie.nowPlaying

import com.odogwudev.example.movie_api.MovieService
import kotlinx.coroutines.flow.filterNotNull

class GetNowPlayingMoviesFlowUseCase(private val movieService: MovieService) {

    operator fun invoke() = movieService.nowPlayingMovies.filterNotNull()
}