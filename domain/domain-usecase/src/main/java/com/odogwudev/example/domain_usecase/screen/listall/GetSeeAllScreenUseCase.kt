package com.odogwudev.example.domain_usecase.screen.listall

import com.odogwudev.example.domain_usecase.helper.movie.nowPlaying.GetNowPlayingMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.topRated.GetTopRatedMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.trending.GetPopularMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.people.GetPopularPeopleUseCase
import com.odogwudev.example.pagination_api.RefreshType

class GetSeeAllScreenUseCase(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getNowPlayingMoviesCase: GetNowPlayingMoviesUseCase,
    private val getPopularPeopleUseCase: GetPopularPeopleUseCase
) {
    suspend operator fun invoke(refreshType: RefreshType, contentType: String) = when (contentType) {
        SeeAllContentType.POPULAR_MOVIES.name -> getPopularMoviesUseCase(refreshType = refreshType)
        SeeAllContentType.POPULAR_PEOPLE.name -> getPopularPeopleUseCase(refreshType = refreshType)
        SeeAllContentType.NOW_PLAYING_MOVIES.name -> getNowPlayingMoviesCase(refreshType = refreshType)
        SeeAllContentType.TOP_RATED_MOVIES.name -> getTopRatedMoviesUseCase(refreshType = refreshType)
        else -> throw IllegalStateException("Invalid SeeAllContentType: $contentType")
    }
}