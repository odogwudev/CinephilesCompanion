package com.odogwudev.example.domain_usecase.auth

import android.content.Intent
import com.odogwudev.example.firebase_api.AuthenticationService
import kotlinx.coroutines.flow.filterNotNull

class LoginWithGoogleAccountUseCase(private val service: AuthenticationService) {

    operator fun invoke(intent: Intent) = service.loginWithGoogleAccount(intent = intent).filterNotNull()
}
