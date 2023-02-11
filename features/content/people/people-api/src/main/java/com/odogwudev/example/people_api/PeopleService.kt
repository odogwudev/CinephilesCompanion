package com.odogwudev.example.people_api

import com.odogwudev.example.pagination_api.PagerItem
import com.odogwudev.example.pagination_api.RefreshType
import kotlinx.coroutines.flow.Flow

interface PeopleService {

    val popularPeople: Flow<List<PagerItem>>

    suspend fun getPopularPeople(refreshType: RefreshType): List<PagerItem>

    fun clearPopularPeople()
}