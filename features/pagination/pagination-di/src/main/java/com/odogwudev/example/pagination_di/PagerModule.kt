package com.odogwudev.example.pagination_di

import com.odogwudev.example.pagination_api.Pager
import com.odogwudev.example.pagination_implementation.PagerImplementation
import org.koin.dsl.module

val pagerModule = module { factory<Pager> { PagerImplementation() } }