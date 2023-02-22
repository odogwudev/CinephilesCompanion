package com.odogwudev.example.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.odogwudev.example.domain_usecase.screen.listall.SeeAllContentType
import com.odogwudev.example.explore.ExploreScreen
import com.odogwudev.example.explore.rememberExploreScreenState
import com.odogwudev.example.favorites.FavouritesScreen
import com.odogwudev.example.home.HomeScreen
import com.odogwudev.example.home.HomeScreenState
import com.odogwudev.example.home.rememberHomeScreenState
import com.odogwudev.example.profile.ProfileScreen
import com.odogwudev.example.seeall.SeeAllScreen
import com.odogwudev.example.seeall.SeeAllScreenState
import com.odogwudev.example.seeall.rememberSeeAllScreenState

fun NavGraphBuilder.mainNavigation(navController: NavHostController) {
    navigation(
        startDestination = Route.Main.HOME,
        route = Route.Main.route
    ) {
        composable(route = Route.Main.HOME) {
            HomeScreen(screenState = rememberHomeScreenState().apply {
                when (action?.consume()) {
                    is HomeScreenState.Action.SeeAllNowPlayingMovies ->
                        navController.navigateToSeeAll(contentType = SeeAllContentType.NOW_PLAYING_MOVIES.name)
                    is HomeScreenState.Action.SeeAllPopularMovies ->
                        navController.navigateToSeeAll(contentType = SeeAllContentType.POPULAR_MOVIES.name)
                    is HomeScreenState.Action.SeeAllPopularPeople ->
                        navController.navigateToSeeAll(contentType = SeeAllContentType.POPULAR_PEOPLE.name)
                    is HomeScreenState.Action.SeeAllTopRatedMovies ->
                        navController.navigateToSeeAll(contentType = SeeAllContentType.TOP_RATED_MOVIES.name)
                    else -> Unit
                }
            })
        }

        composable(route = Route.Main.EXPLORE) {
            ExploreScreen(screenState = rememberExploreScreenState())
        }

        composable(route = Route.Main.FAVOURITES) {
            FavouritesScreen()
        }

        composable(route = Route.Main.PROFILE) {
            ProfileScreen()
        }

        composable(route = Route.Main.SEE_ALL) { backstackEntry ->
            val contentType = backstackEntry.arguments?.getString("contentType") as String

            SeeAllScreen(screenState = rememberSeeAllScreenState(contentType = contentType).apply {
                when (action?.consume()) {
                    is SeeAllScreenState.Action.NavigateUp -> navController.navigateUp()
                    else -> Unit
                }
            })
        }
    }
}

fun NavHostController.navigateToSeeAll(contentType: String) {
    navigate(route = "SeeAll/${contentType}")
}
