package com.odogwudev.example.domain_usecase.helper.people

import com.odogwudev.example.people_api.PeopleService
import kotlinx.coroutines.flow.filterNotNull

class GetPopularPeopleFlowUseCase(private val peopleService: PeopleService) {

    operator fun invoke() = peopleService.popularPeople.filterNotNull()
}