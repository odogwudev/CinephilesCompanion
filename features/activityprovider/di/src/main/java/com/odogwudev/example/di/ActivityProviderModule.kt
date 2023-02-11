package com.odogwudev.example.di

import com.odogwudev.example.api.ActivityProvider
import com.odogwudev.example.api.ActivitySetter
import com.odogwudev.example.implementation.ActivityProviderImp
import org.koin.core.module.Module
import org.koin.dsl.module

val activityProviderModule: Module = module {
    single<ActivityProvider> { get<ActivityProviderImp>() }
    single<ActivitySetter> { get<ActivityProviderImp>() }
    single { ActivityProviderImp() }
}
