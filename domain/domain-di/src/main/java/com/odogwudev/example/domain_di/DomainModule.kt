package com.odogwudev.example.domain_di

import com.odogwudev.example.domain_usecase.auth.*
import com.odogwudev.example.domain_usecase.helper.movie.discover.DiscoverMoviesFlowUseCase
import com.odogwudev.example.domain_usecase.helper.movie.discover.DiscoverMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.nowPlaying.DeleteNowPlayingMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.nowPlaying.GetNowPlayingMoviesFlowUseCase
import com.odogwudev.example.domain_usecase.helper.movie.nowPlaying.GetNowPlayingMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.search.DeleteSearchMovieUseCase
import com.odogwudev.example.domain_usecase.helper.movie.search.SearchMoviesFlowUseCase
import com.odogwudev.example.domain_usecase.helper.movie.search.SearchMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.topRated.DeleteTopRatedMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.topRated.GetTopRatedMoviesFlowUseCase
import com.odogwudev.example.domain_usecase.helper.movie.topRated.GetTopRatedMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.trending.DeletePopularMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.trending.GetPopularMoviesFlowUseCase
import com.odogwudev.example.domain_usecase.helper.movie.trending.GetPopularMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.upcoming.DeleteUpcomingMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.movie.upcoming.GetUpcomingMoviesFlowUseCase
import com.odogwudev.example.domain_usecase.helper.movie.upcoming.GetUpcomingMoviesUseCase
import com.odogwudev.example.domain_usecase.helper.people.DeletePopularPeopleUseCase
import com.odogwudev.example.domain_usecase.helper.people.GetPopularPeopleFlowUseCase
import com.odogwudev.example.domain_usecase.helper.people.GetPopularPeopleUseCase
import com.odogwudev.example.domain_usecase.helper.tvSeries.discover.DiscoverTvSeriesFlowUseCase
import com.odogwudev.example.domain_usecase.helper.tvSeries.discover.DiscoverTvSeriesUseCase
import com.odogwudev.example.domain_usecase.helper.tvSeries.search.DeleteSearchTvSeriesUseCase
import com.odogwudev.example.domain_usecase.helper.tvSeries.search.SearchTvSeriesFlowUseCase
import com.odogwudev.example.domain_usecase.helper.tvSeries.search.SearchTvSeriesUseCase
import com.odogwudev.example.domain_usecase.screen.explore.discover.DiscoverContentFlowUseCase
import com.odogwudev.example.domain_usecase.screen.explore.discover.DiscoverContentUseCase
import com.odogwudev.example.domain_usecase.screen.explore.search.DeleteContentUseCase
import com.odogwudev.example.domain_usecase.screen.explore.search.SearchContentFlowUseCase
import com.odogwudev.example.domain_usecase.screen.explore.search.SearchContentUseCase
import com.odogwudev.example.domain_usecase.screen.home.GetHomeScreenFlowUseCase
import com.odogwudev.example.domain_usecase.screen.home.GetHomeScreenUseCase
import com.odogwudev.example.domain_usecase.screen.listall.GetSeeAllScreenFlowUseCase
import com.odogwudev.example.domain_usecase.screen.listall.GetSeeAllScreenUseCase
import com.odogwudev.example.explore_di.createExploreModule
import com.odogwudev.example.firebase_di.createAuthenticationModule
import com.odogwudev.example.movie_di.createMovieModule
import com.odogwudev.example.people_di.createPeopleModule
import org.koin.dsl.module

fun createDomainModules() = buildList {
    addAll(createServiceModules())
    add(createUseCaseModules())
}

private fun createServiceModules() = buildList {
    add(createExploreModule())
    add(createMovieModule())
    add(createPeopleModule())
    add(createAuthenticationModule())
}

private fun createUseCaseModules() = module {
    // Auth
    factory { GetIntentForGoogleAccountLoginUseCase(service = get()) }
    factory { IsLoggedInUseCase(service = get()) }
    factory { LoginWithEmailAndPasswordUseCase(service = get()) }
    factory { LoginWithGoogleAccountUseCase(service = get()) }
    factory { LoginWithFacebookAccountUseCase(service = get()) }
    factory { LogOutUseCase(service = get()) }
    factory { RegisterWithEmailAndPasswordUseCase(service = get()) }
    factory { ResetPasswordUseCase(service = get()) }

    // Movie [Popular]
    factory { GetPopularMoviesUseCase(movieService = get()) }
    factory { GetPopularMoviesFlowUseCase(movieService = get()) }
    factory { DeletePopularMoviesUseCase(movieService = get()) }

    // Movie [Upcoming]
    factory { GetUpcomingMoviesUseCase(movieService = get()) }
    factory { GetUpcomingMoviesFlowUseCase(movieService = get()) }
    factory { DeleteUpcomingMoviesUseCase(movieService = get()) }

    // Movie [Top Rated]
    factory { GetTopRatedMoviesUseCase(movieService = get()) }
    factory { GetTopRatedMoviesFlowUseCase(movieService = get()) }
    factory { DeleteTopRatedMoviesUseCase(movieService = get()) }

    // Movie [Now Playing]
    factory { GetNowPlayingMoviesUseCase(movieService = get()) }
    factory { GetNowPlayingMoviesFlowUseCase(movieService = get()) }
    factory { DeleteNowPlayingMoviesUseCase(movieService = get()) }

    // People [Popular]
    factory { GetPopularPeopleUseCase(peopleService = get()) }
    factory { GetPopularPeopleFlowUseCase(peopleService = get()) }
    factory { DeletePopularPeopleUseCase(peopleService = get()) }

    // Discover [Movie]
    factory { DiscoverMoviesUseCase(exploreService = get()) }
    factory { DiscoverMoviesFlowUseCase(exploreService = get()) }

    // Search [Movie]
    factory { SearchMoviesUseCase(exploreService = get()) }
    factory { SearchMoviesFlowUseCase(exploreService = get()) }
    factory { DeleteSearchMovieUseCase(exploreService = get()) }

    // Discover [TvSeries]
    factory { DiscoverTvSeriesUseCase(exploreService = get()) }
    factory { DiscoverTvSeriesFlowUseCase(exploreService = get()) }

    // Search [TvSeries]
    factory { SearchTvSeriesUseCase(exploreService = get()) }
    factory { SearchTvSeriesFlowUseCase(exploreService = get()) }
    factory { DeleteSearchTvSeriesUseCase(exploreService = get()) }

    // Home
    factory {
        GetHomeScreenUseCase(
            getPopularMoviesUseCase = get(),
            getUpcomingMoviesUseCase = get(),
            getTopRatedMoviesUseCase = get(),
            getNowPlayingMoviesCase = get(),
            getPopularPeopleUseCase = get()
        )
    }
    factory {
        GetHomeScreenFlowUseCase(
            getPopularMoviesFlowUseCase = get(),
            getUpcomingMoviesFlowUseCase = get(),
            getTopRatedMoviesFlowUseCase = get(),
            getNowPlayingMoviesFlowCase = get(),
            getPopularPeopleFlowUseCase = get()
        )
    }

    // Explore
    factory {
        DiscoverContentUseCase(
            discoverMoviesUseCase = get(),
            discoverTvSeriesUseCase = get()
        )
    }
    factory {
        DiscoverContentFlowUseCase(
            discoverMoviesFlowUseCase = get(),
            discoverTvSeriesFlowUseCase = get()
        )
    }
    factory {
        SearchContentUseCase(
            searchMoviesUseCase = get(),
            searchTvSeriesUseCase = get()
        )
    }
    factory {
        SearchContentFlowUseCase(
            searchMoviesFlowUseCase = get(),
            searchTvSeriesFlowUseCase = get()
        )
    }
    factory {
        DeleteContentUseCase(
            deleteSearchMovieUseCase = get(),
            deleteSearchTvSeriesUseCase = get()
        )
    }

    // See All
    factory {
        GetSeeAllScreenUseCase(
            getPopularMoviesUseCase = get(),
            getTopRatedMoviesUseCase = get(),
            getNowPlayingMoviesCase = get(),
            getPopularPeopleUseCase = get()
        )
    }
    factory {
        GetSeeAllScreenFlowUseCase(
            getPopularMoviesFlowUseCase = get(),
            getTopRatedMoviesFlowUseCase = get(),
            getNowPlayingMoviesFlowCase = get(),
            getPopularPeopleFlowUseCase = get()
        )
    }
}