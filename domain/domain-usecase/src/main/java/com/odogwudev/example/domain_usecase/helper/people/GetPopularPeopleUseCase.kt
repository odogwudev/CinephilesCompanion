package com.odogwudev.example.domain_usecase.helper.people

import com.odogwudev.example.domain_util.result.wrapToResult
import com.odogwudev.example.pagination_api.RefreshType
import com.odogwudev.example.people_api.PeopleService

class GetPopularPeopleUseCase(private val peopleService: PeopleService) {

    suspend operator fun invoke(refreshType: RefreshType) = wrapToResult {
        peopleService.getPopularPeople(refreshType = refreshType).distinctBy { it.id }
    }
}