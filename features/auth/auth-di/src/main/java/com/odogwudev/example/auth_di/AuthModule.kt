package com.odogwudev.example.auth_di

import com.halcyonmobile.oauth.dependencies.AuthenticationLocalStorage
import com.halcyonmobile.oauth.dependencies.SessionExpiredEventHandler
import com.odogwudev.example.auth_implementation.AuthenticationLocalStorageImpl
import com.odogwudev.example.auth_implementation.SessionExpiredEventHandlerImpl
import org.koin.dsl.module

fun createAuthModules() = buildList {
    add(createAuthenticationStorageModule())
    add(createSessionExpiredHandlerModule())
}

private fun createAuthenticationStorageModule() = module {
    single { AuthenticationLocalStorageImpl(context = get()) }
    single <AuthenticationLocalStorage> { get<AuthenticationLocalStorageImpl>() }
}

private fun createSessionExpiredHandlerModule() = module {
    single<SessionExpiredEventHandler> { get<SessionExpiredEventHandlerImpl>() }
    single { SessionExpiredEventHandlerImpl() }
}