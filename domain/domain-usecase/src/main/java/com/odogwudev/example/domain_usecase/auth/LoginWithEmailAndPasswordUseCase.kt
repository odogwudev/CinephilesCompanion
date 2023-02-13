package com.odogwudev.example.domain_usecase.auth

import com.odogwudev.example.firebase_api.AuthResult
import com.odogwudev.example.firebase_api.AuthenticationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull

class LoginWithEmailAndPasswordUseCase(private val service: AuthenticationService) {

    operator fun invoke(email: String, password: String): Flow<AuthResult> =
        service.loginWithEmailAndPassword(email = email, password = password).filterNotNull()
}
