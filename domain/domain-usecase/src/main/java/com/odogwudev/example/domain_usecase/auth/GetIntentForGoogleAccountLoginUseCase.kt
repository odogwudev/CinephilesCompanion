package com.odogwudev.example.domain_usecase.auth

import com.odogwudev.example.firebase_api.AuthenticationService

class GetIntentForGoogleAccountLoginUseCase(private val service: AuthenticationService) {

    operator fun invoke() = service.getIntentForGoogleAccountLogin()
}
