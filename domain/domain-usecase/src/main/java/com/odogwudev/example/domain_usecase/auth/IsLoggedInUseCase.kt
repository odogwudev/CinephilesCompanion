package com.odogwudev.example.domain_usecase.auth

import com.odogwudev.example.firebase_api.AuthenticationService
import kotlinx.coroutines.flow.filterNotNull

class IsLoggedInUseCase(private val service: AuthenticationService) {

    operator fun invoke() = service.authenticationState.filterNotNull()
}