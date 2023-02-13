package com.odogwudev.example.domain_usecase.helper.movie.nowPlaying

import com.odogwudev.example.movie_api.MovieService

class DeleteNowPlayingMoviesUseCase(private val movieService: MovieService) {

    operator fun invoke() = movieService.clearNowPlayingMovies()
}