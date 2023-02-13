package com.odogwudev.example.domain_usecase.helper.people

import com.odogwudev.example.people_api.PeopleService

class DeletePopularPeopleUseCase(private val peopleService: PeopleService) {

    operator fun invoke() = peopleService.clearPopularPeople()
}