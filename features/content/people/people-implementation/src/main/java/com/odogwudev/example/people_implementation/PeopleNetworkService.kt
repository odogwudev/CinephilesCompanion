package com.odogwudev.example.people_implementation

import com.odogwudev.example.people.dto.PeopleListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleNetworkService {

    @GET("person/popular")
    suspend fun getPopularPeople(@Query("page") page: Int) : PeopleListResponse
}