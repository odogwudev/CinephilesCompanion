package com.odogwudev.example.domain_usecase.screen.home

import com.odogwudev.example.domain_usecase.helper.movie.nowPlaying.GetNowPlayingMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.topRated.GetTopRatedMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.trending.GetPopularMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.upcoming.GetUpcomingMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.people.GetPopularPeopleUseCase
import com.odogwudev.example.domain_util.result.asyncWrapToResult
import com.odogwudev.example.pagination_api.RefreshType
import kotlinx.coroutines.CoroutineScope

class GetHomeScreenUseCase(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getNowPlayingMoviesCase: GetNowPlayingMoviesUseCase,
    private val getPopularPeopleUseCase: GetPopularPeopleUseCase,
) {

    suspend operator fun invoke(
        coroutineScope: CoroutineScope,
        refreshType: RefreshType
    ) = asyncWrapToResult(
        scope = coroutineScope,
        functions = listOf(
            getUpcomingMoviesUseCase(refreshType = refreshType),
            getPopularMoviesUseCase(refreshType = refreshType),
            getTopRatedMoviesUseCase(refreshType = refreshType),
            getNowPlayingMoviesCase(refreshType = refreshType),
            getPopularPeopleUseCase(refreshType = refreshType)
        )
    )
}