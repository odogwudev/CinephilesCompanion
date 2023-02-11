package com.odogwudev.example.people_di

import com.odogwudev.example.network_api.RetrofitClient
import com.odogwudev.example.people_api.PeopleService
import com.odogwudev.example.people_implementation.PeopleNetworkService
import com.odogwudev.example.people_implementation.PeopleRemoteSource
import com.odogwudev.example.people_implementation.PeopleServiceImpl
import org.koin.dsl.module

fun createPeopleModule() = module {
    factory { get<RetrofitClient>().sessionless.create(PeopleNetworkService::class.java) }
    factory { PeopleRemoteSource(networkService = get()) }
    single<PeopleService> { PeopleServiceImpl(remoteSource = get(), pager = get()) }
}