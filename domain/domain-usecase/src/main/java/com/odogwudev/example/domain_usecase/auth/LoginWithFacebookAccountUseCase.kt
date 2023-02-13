package com.odogwudev.example.domain_usecase.auth

import com.odogwudev.example.firebase_api.AuthenticationService
import kotlinx.coroutines.flow.filterNotNull

class LoginWithFacebookAccountUseCase(private val service: AuthenticationService) {

    operator fun invoke() = service.loginWithFacebookAccount().filterNotNull()
}