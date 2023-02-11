package com.odogwudev.example.people_implementation

import com.odogwudev.example.people.dto.toModel

class PeopleRemoteSource(private val networkService: PeopleNetworkService) {

    suspend fun getPopularPeople(page: Int) = networkService.getPopularPeople(page = page).toModel()
}