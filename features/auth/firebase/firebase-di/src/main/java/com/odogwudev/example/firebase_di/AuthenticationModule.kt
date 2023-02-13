package com.odogwudev.example.firebase_di

import com.odogwudev.example.firebase_api.AuthenticationService
import com.odogwudev.example.firebase_implementation.AuthenticationServiceImpl
import org.koin.dsl.module

fun createAuthenticationModule() = module {
    single<AuthenticationService>{ AuthenticationServiceImpl(activityProvider = get()) }
}
