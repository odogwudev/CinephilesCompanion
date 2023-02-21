package com.odogwudev.example.welcome

import androidx.compose.runtime.*
import com.odogwudev.example.utils.Event

@Composable
fun rememberWelcomeScreenState() = remember { WelcomeScreenState() }

class WelcomeScreenState {

    var action by mutableStateOf<Event<Action>?>(value = null)
        private set

    fun navigateToAuth() {
        action = Event(data = Action.NavigateToAuth)
    }

    sealed class Action {
        object NavigateToAuth : Action()
    }
}