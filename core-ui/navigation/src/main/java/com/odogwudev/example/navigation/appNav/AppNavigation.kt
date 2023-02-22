package com.odogwudev.example.navigation.appNav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.odogwudev.example.firebase_api.AuthenticationState
import com.odogwudev.example.navigation.Route
import com.odogwudev.example.navigation.authNavigation
import com.odogwudev.example.navigation.bottomNav.BottomNavHolder
import com.odogwudev.example.navigation.mainNavigation

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val appNavigationState = rememberAppNavigationState()

    Column {
        NavHost(
            navController = navController,
            startDestination = Route.Splash.route,
            builder = {
                composable(route = Route.Splash.route) {
                    /**TODO: Add some fancy splash screen*/
                    Spacer(modifier = Modifier.fillMaxSize())
                }
                authNavigation(navController = navController)
                mainNavigation(navController = navController)
            },
            modifier = Modifier.weight(weight = 1f)
        )
        BottomNavHolder(navController = navController, navBackStackEntry = navBackStackEntry)
    }

    LaunchedEffect(
        key1 = appNavigationState.authState,
        block = {
            when (appNavigationState.authState) {
                AuthenticationState.Logged -> navController.navigateToMain()
                AuthenticationState.NotLogged -> navController.navigateToAuth()
                else -> Unit
            }
        }
    )
}

fun NavHostController.navigateToAuth() {
    navigate(route = Route.Authentication.route) {
        popUpTo(id = this@navigateToAuth.graph.id) {
            inclusive = true
        }
    }
}

fun NavHostController.navigateToMain() {
    navigate(route = Route.Main.route) {
        popUpTo(id = this@navigateToMain.graph.id) {
            inclusive = true
        }
    }
}