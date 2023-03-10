package com.odogwudev.example.movie_implementation

import com.odogwudev.example.movie_api.MovieService
import com.odogwudev.example.pagination_api.Pager
import com.odogwudev.example.pagination_api.PagerItem
import com.odogwudev.example.pagination_api.RefreshType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MovieServiceImpl(
    private val remoteSource: MovieRemoteSource,
    private val pager: Pager
) : MovieService {

    private val _popularMovies = MutableStateFlow<List<PagerItem>>(value = emptyList())
    override val popularMovies: Flow<List<PagerItem>> = _popularMovies

    private val _upcomingMovies = MutableStateFlow<List<PagerItem>>(value = emptyList())
    override val upcomingMovies: Flow<List<PagerItem>> = _upcomingMovies

    private val _topRatedMovies = MutableStateFlow<List<PagerItem>>(value = emptyList())
    override val topRatedMovies: Flow<List<PagerItem>> = _topRatedMovies

    private val _nowPlayingMovies = MutableStateFlow<List<PagerItem>>(value = emptyList())
    override val nowPlayingMovies: Flow<List<PagerItem>> = _nowPlayingMovies

    override suspend fun getUpcomingMovies(refreshType: RefreshType): List<PagerItem> =
        pager.paginate(
            refreshType = refreshType,
            flow = _upcomingMovies,
            getRemoteContent = { page -> remoteSource.getUpcomingMovies(page = page) },
            cacheWithError = false
        )

    override suspend fun getPopularMovies(refreshType: RefreshType): List<PagerItem> =
        pager.paginate(
            refreshType = refreshType,
            flow = _popularMovies,
            getRemoteContent = { page -> remoteSource.getPopularMovies(page = page) },
            cacheWithError = false
        )

    override suspend fun getTopRatedMovies(refreshType: RefreshType): List<PagerItem> =
        pager.paginate(
            refreshType = refreshType,
            flow = _topRatedMovies,
            getRemoteContent = { page -> remoteSource.getTopRatedMovies(page = page) },
            cacheWithError = false
        )

    override suspend fun getNowPlayingMovies(refreshType: RefreshType): List<PagerItem> =
        pager.paginate(
            refreshType = refreshType,
            flow = _nowPlayingMovies,
            getRemoteContent = { page -> remoteSource.getNowPlayingMovies(page = page) },
            cacheWithError = false
        )

    override fun clearPopularMovies() {
        _popularMovies.value = emptyList()
    }

    override fun clearUpcomingMovies() {
        _upcomingMovies.value = emptyList()
    }

    override fun clearTopRatedMovies() {
        _topRatedMovies.value = emptyList()
    }

    override fun clearNowPlayingMovies() {
        _nowPlayingMovies.value = emptyList()
    }
}