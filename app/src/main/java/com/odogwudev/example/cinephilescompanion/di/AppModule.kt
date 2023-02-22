package com.odogwudev.example.cinephilescompanion.di

import com.odogwudev.example.auth_di.createAuthModules
import com.odogwudev.example.di.activityProviderModule
import com.odogwudev.example.domain_di.createDomainModules
import com.odogwudev.example.networking_di.createNetworkModule
import com.odogwudev.example.pagination_di.pagerModule
import com.odogwudev.example.cinephilescompanion.BuildConfig

fun createAppModule() = buildList {
    add(activityProviderModule)
    add(createNetworkModule(baseUrl = BuildConfig.BASE_URL, apiKey = BuildConfig.API_KEY, clientId = BuildConfig.CLIENT_ID))
    addAll(createAuthModules())
    addAll(createDomainModules())
    add(pagerModule)
}