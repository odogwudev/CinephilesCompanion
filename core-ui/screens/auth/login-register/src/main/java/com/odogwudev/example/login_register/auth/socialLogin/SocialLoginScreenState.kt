package com.odogwudev.example.login_register.auth.socialLogin

import android.content.Intent
import androidx.compose.runtime.*
import com.odogwudev.example.domain_usecase.auth.GetIntentForGoogleAccountLoginUseCase
import com.odogwudev.example.domain_usecase.auth.LoginWithFacebookAccountUseCase
import com.odogwudev.example.domain_usecase.auth.LoginWithGoogleAccountUseCase
import com.odogwudev.example.firebase_api.AuthResult
import com.odogwudev.example.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun rememberSocialLoginScreenState(
    scope: CoroutineScope = rememberCoroutineScope(),
    getIntentForGoogleAccountLogin: GetIntentForGoogleAccountLoginUseCase = get(),
    loginWithGoogleAccountUseCase: LoginWithGoogleAccountUseCase = get(),
    loginWithFacebookAccountUseCase: LoginWithFacebookAccountUseCase = get()
) = remember {
    SocialLoginScreenState(
        scope = scope,
        getIntentForGoogleAccountLogin = getIntentForGoogleAccountLogin,
        loginWithGoogleAccountUseCase = loginWithGoogleAccountUseCase,
        loginWithFacebookAccountUseCase = loginWithFacebookAccountUseCase
    )
}

class SocialLoginScreenState(
    private val scope: CoroutineScope,
    private val getIntentForGoogleAccountLogin: GetIntentForGoogleAccountLoginUseCase,
    private val loginWithGoogleAccountUseCase: LoginWithGoogleAccountUseCase,
    private val loginWithFacebookAccountUseCase: LoginWithFacebookAccountUseCase
) {

    var state by mutableStateOf<State>(value = State.Normal)
        private set
    var action by mutableStateOf<Event<Action>?>(value = null)
        private set

    fun loginWithFacebook() {
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

    fun loginWithGoogle(intent: Intent) {
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

    fun onSignInClicked() {
        action = Event(data = Action.NavigateToLogin)
    }

    fun onSignUpClicked() {
        action = Event(data = Action.NavigateToRegister)
    }

    fun resetState() { state = State.Normal
    }

    sealed class State {
        object Normal: State()
        object Loading: State()
        data class Error(val message: String): State()
    }

    sealed class Action {
        object NavigateToLogin : Action()
        object NavigateToRegister : Action()
    }
}