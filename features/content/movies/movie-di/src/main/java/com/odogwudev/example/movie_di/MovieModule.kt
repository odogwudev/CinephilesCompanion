package com.odogwudev.example.movie_di

import com.odogwudev.example.movie_api.MovieService
import com.odogwudev.example.movie_implementation.MovieNetworkService
import com.odogwudev.example.movie_implementation.MovieRemoteSource
import com.odogwudev.example.movie_implementation.MovieServiceImpl
import com.odogwudev.example.network_api.RetrofitClient
import org.koin.dsl.module

fun createMovieModule() = module {
    factory { get<RetrofitClient>().sessionless.create(MovieNetworkService::class.java) }
    factory { MovieRemoteSource(networkService = get()) }
    single<MovieService> { MovieServiceImpl(remoteSource = get(), pager = get()) }
}