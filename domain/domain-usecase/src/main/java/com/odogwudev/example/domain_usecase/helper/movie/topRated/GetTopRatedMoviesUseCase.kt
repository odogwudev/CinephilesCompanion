package com.odogwudev.example.domain_usecase.helper.movie.topRated

import com.odogwudev.example.domain_util.result.wrapToResult
import com.odogwudev.example.movie_api.MovieService
import com.odogwudev.example.pagination_api.RefreshType

class GetTopRatedMoviesUseCase(private val movieService: MovieService) {

    suspend operator fun invoke(refreshType: RefreshType) = wrapToResult {
        movieService.getTopRatedMovies(refreshType = refreshType).distinctBy { it.id }
    }
}