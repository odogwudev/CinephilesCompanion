package com.odogwudev.example.navigation.appNav

import androidx.compose.runtime.*
import com.odogwudev.example.domain_usecase.auth.IsLoggedInUseCase
import com.odogwudev.example.firebase_api.AuthenticationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun rememberAppNavigationState(
    scope: CoroutineScope = rememberCoroutineScope(),
    isLoggedInUseCase: IsLoggedInUseCase = get(),
): AppNavigationState = remember {
    AppNavigationState(
        scope = scope,
        isLoggedInUseCase = isLoggedInUseCase
    )
}


class AppNavigationState(
    private val scope: CoroutineScope,
    private val isLoggedInUseCase: IsLoggedInUseCase
) {
    var authState by mutableStateOf<AuthenticationState?>(value = null)
        private set

    init {
        scope.launch {
            isLoggedInUseCase().collect {
                authState = it
            }
        }
    }
}