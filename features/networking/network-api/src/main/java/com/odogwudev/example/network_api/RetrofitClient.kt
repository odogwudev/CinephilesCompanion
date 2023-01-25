package com.odogwudev.example.network_api

import retrofit2.Retrofit

interface RetrofitClient {
    val session: Retrofit
    val sessionless: Retrofit
}