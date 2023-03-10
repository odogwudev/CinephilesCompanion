package com.odogwudev.example.login_register.auth

import android.content.Intent
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import com.odogwudev.example.domain_usecase.auth.*
import com.odogwudev.example.firebase_api.AuthResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

enum class ScreenType {
    LOGIN, REGISTER
}

@Composable
fun rememberAuthScreenState(
    screenType: String,
    scope: CoroutineScope = rememberCoroutineScope(),
    loginWithEmailAndPassword: LoginWithEmailAndPasswordUseCase = get(),
    registerWithEmailAndPassword: RegisterWithEmailAndPasswordUseCase = get(),
    getIntentForGoogleAccountLogin: GetIntentForGoogleAccountLoginUseCase = get(),
    loginWithGoogleAccountUseCase: LoginWithGoogleAccountUseCase = get(),
    loginWithFacebookAccountUseCase: LoginWithFacebookAccountUseCase = get()
): AuthScreenState = rememberSaveable(
    saver = AuthScreenState.getSaver(
        screenType = screenType,
        scope = scope,
        loginWithEmailAndPassword = loginWithEmailAndPassword,
        registerWithEmailAndPassword = registerWithEmailAndPassword,
        getIntentForGoogleAccountLogin = getIntentForGoogleAccountLogin,
        loginWithGoogleAccountUseCase = loginWithGoogleAccountUseCase,
        loginWithFacebookAccountUseCase = loginWithFacebookAccountUseCase
    )
) {
    AuthScreenState(
        screenType = screenType,
        scope = scope,
        loginWithEmailAndPassword = loginWithEmailAndPassword,
        registerWithEmailAndPassword = registerWithEmailAndPassword,
        getIntentForGoogleAccountLogin = getIntentForGoogleAccountLogin,
        loginWithGoogleAccountUseCase = loginWithGoogleAccountUseCase,
        loginWithFacebookAccountUseCase = loginWithFacebookAccountUseCase
    )
}

class AuthScreenState(
    val screenType: String,
    private val scope: CoroutineScope,
    private val loginWithEmailAndPassword: LoginWithEmailAndPasswordUseCase,
    private val registerWithEmailAndPassword: RegisterWithEmailAndPasswordUseCase,
    private val getIntentForGoogleAccountLogin: GetIntentForGoogleAccountLoginUseCase,
    private val loginWithGoogleAccountUseCase: LoginWithGoogleAccountUseCase,
    private val loginWithFacebookAccountUseCase: LoginWithFacebookAccountUseCase
) {

    var state by mutableStateOf<State>(value = State.Normal)
        private set
    var screenProperty by mutableStateOf<ScreenProperty?>(value = null)
        private set
    var email by mutableStateOf(value = "")
        private set
    var password by mutableStateOf(value = "")
        private set
    val isAuthEnabled by derivedStateOf { email.isNotEmpty() && password.isNotEmpty() }

    init {
        initScreenProperty()
    }

    private fun initScreenProperty() {
        screenProperty = when (screenType) {
            ScreenType.LOGIN.name -> ScreenProperty.Login
            ScreenType.REGISTER.name -> ScreenProperty.Register
            else -> null
        }
    }

    fun authenticate() {
        if (isAuthEnabled) {
            state = State.Loading
            scope.launch {
                if (screenProperty is ScreenProperty.Login) {
                    loginWithEmailAndPassword(email = email, password = password)
                } else {
                    registerWithEmailAndPassword(email = email, password = password)
                }.onEach { authResult ->
                    state = when (authResult) {
                        is AuthResult.Failure -> State.Error(message = authResult.error)
                        is AuthResult.Success, is AuthResult.Dismissed -> State.Normal
                    }

                }.stateIn(scope = this)
            }
        }
    }

    fun authenticateWithFacebook() {
        state = State.Loading
        scope.launch {
            loginWithFacebookAccountUseCase().onEach { result ->
                state = when (result) {
                    is AuthResult.Failure -> State.Error(message = result.error)
                    is AuthResult.Dismissed -> State.Error(message = result.error.orEmpty())
                    is AuthResult.Success -> State.Normal
                }
            }.stateIn(scope = this)
        }
    }

    fun authenticateWithGoogle(intent: Intent) {
        state = State.Loading
        scope.launch {
            loginWithGoogleAccountUseCase(intent = intent).onEach { result ->
                state = when (result) {
                    is AuthResult.Failure -> State.Error(message = result.error)
                    is AuthResult.Success, is AuthResult.Dismissed -> State.Normal
                }
            }.stateIn(scope = this)
        }
    }

    fun getIntentForGoogleLogin(): Intent = getIntentForGoogleAccountLogin()

    fun changeAuthScreen() {
        screenProperty = if (screenProperty is ScreenProperty.Login) ScreenProperty.Register else ScreenProperty.Login
    }

    fun onEmailChange(value: String) { email = value }

    fun onPasswordChange(value: String) { password = value }

    fun resetState() { state = State.Normal
    }

    sealed class State {
        object Normal : State()
        object Loading : State()
        data class Error(val message: String) : State()
    }

    sealed class ScreenProperty {
        abstract val screenTitle: String
        abstract val authButtonText: String
        abstract val authFooterQuestion: String
        abstract val authFooterText: String

        object Login : ScreenProperty() {
            override val screenTitle: String = "Login to Your Account"
            override val authButtonText: String = SIGN_IN
            override val authFooterQuestion: String = "Don't have an account?"
            override val authFooterText: String = SIGN_UP
        }

        object Register : ScreenProperty() {
            override val screenTitle: String = "Create Your Account"
            override val authButtonText: String = SIGN_UP
            override val authFooterQuestion: String = "Already have an account?"
            override val authFooterText: String = SIGN_IN
        }
    }

    companion object {
        private const val EMAIL_KEY: String = "email"
        private const val PASSWORD_KEY: String = "password"
        private const val SIGN_UP: String = "Sign up"
        private const val SIGN_IN: String = "Sign in"

        fun getSaver(
            screenType: String,
            scope: CoroutineScope,
            loginWithEmailAndPassword: LoginWithEmailAndPasswordUseCase,
            registerWithEmailAndPassword: RegisterWithEmailAndPasswordUseCase,
            getIntentForGoogleAccountLogin: GetIntentForGoogleAccountLoginUseCase,
            loginWithGoogleAccountUseCase: LoginWithGoogleAccountUseCase,
            loginWithFacebookAccountUseCase: LoginWithFacebookAccountUseCase
        ): Saver<AuthScreenState, *> = mapSaver(
            save = { mapOf(EMAIL_KEY to it.email, PASSWORD_KEY to it.password) },
            restore = {
                AuthScreenState(
                    screenType = screenType,
                    scope = scope,
                    loginWithEmailAndPassword = loginWithEmailAndPassword,
                    registerWithEmailAndPassword = registerWithEmailAndPassword,
                    getIntentForGoogleAccountLogin = getIntentForGoogleAccountLogin,
                    loginWithGoogleAccountUseCase = loginWithGoogleAccountUseCase,
                    loginWithFacebookAccountUseCase = loginWithFacebookAccountUseCase
                ).apply {
                    email = it[EMAIL_KEY] as String
                    password = it[PASSWORD_KEY] as String
                }
            }
        )
    }
}