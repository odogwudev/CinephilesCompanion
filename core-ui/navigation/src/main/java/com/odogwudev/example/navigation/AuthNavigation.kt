package com.odogwudev.example.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.odogwudev.example.login_register.auth.AuthScreen
import com.odogwudev.example.login_register.auth.ScreenType
import com.odogwudev.example.login_register.auth.rememberAuthScreenState
import com.odogwudev.example.login_register.auth.socialLogin.SocialLoginScreen
import com.odogwudev.example.login_register.auth.socialLogin.SocialLoginScreenState
import com.odogwudev.example.login_register.auth.socialLogin.rememberSocialLoginScreenState
import com.odogwudev.example.welcome.WelcomeScreen
import com.odogwudev.example.welcome.WelcomeScreenState
import com.odogwudev.example.welcome.rememberWelcomeScreenState

fun NavGraphBuilder.authNavigation(navController: NavHostController) {
    navigation(
        startDestination = Route.Authentication.WELCOME,
        route = Route.Authentication.route
    ) {
        composable(route = Route.Authentication.WELCOME) {
            WelcomeScreen(screenState = rememberWelcomeScreenState().apply {
                when (action?.consume()) {
                    is WelcomeScreenState.Action.NavigateToAuth -> navController.navigateToSocialLogin()
                    else -> Unit
                }
            })
        }

        composable(route = Route.Authentication.SOCIAL_LOGIN) {
            SocialLoginScreen(screenState = rememberSocialLoginScreenState().apply {
                when (action?.consume()) {
                    is SocialLoginScreenState.Action.NavigateToLogin -> navController.navigateToAuthentication(
                        screenType = ScreenType.LOGIN.name
                    )
                    is SocialLoginScreenState.Action.NavigateToRegister -> navController.navigateToAuthentication(
                        screenType = ScreenType.REGISTER.name
                    )
                    else -> Unit
                }
            })
        }

        composable(route = Route.Authentication.AUTH) { backstackEntry ->
            val screenType = backstackEntry.arguments?.getString("screenType") as String

            AuthScreen(screenState = rememberAuthScreenState(screenType = screenType))
        }
    }
}

fun NavHostController.navigateToSocialLogin() {
    navigate(route = Route.Authentication.SOCIAL_LOGIN)
}

fun NavHostController.navigateToAuthentication(screenType: String) {
    navigate(route = "Auth/${screenType}") {
        launchSingleTop = true
    }
}