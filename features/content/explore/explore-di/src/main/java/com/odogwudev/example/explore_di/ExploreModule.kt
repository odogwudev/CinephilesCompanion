package com.odogwudev.example.explore_di

import com.odogwudev.example.explore_api.ExploreService
import com.odogwudev.example.explore_implementation.ExploreNetworkService
import com.odogwudev.example.explore_implementation.ExploreRemoteSource
import com.odogwudev.example.explore_implementation.ExploreServiceImpl
import com.odogwudev.example.network_api.RetrofitClient
import org.koin.dsl.module

fun createExploreModule() = module {
    factory { get<RetrofitClient>().sessionless.create(ExploreNetworkService::class.java) }
    factory { ExploreRemoteSource(networkService = get()) }
    single<ExploreService> { ExploreServiceImpl(remoteSource = get(), pager = get()) }
}

