package com.odogwudev.example.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.odogwudev.example.base.BaseScreen
import com.odogwudev.example.base.UserAction
import com.odogwudev.example.catalog.PeopleCarousel
import com.odogwudev.example.catalog.WatchablePager
import com.odogwudev.example.catalog.WatchableWithRatingCarousel
import com.odogwudev.example.domain_model.ContentItem
import com.odogwudev.example.movie.model.Movie
import com.odogwudev.example.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(screenState: HomeScreenState) = BaseScreen(
    screenState = screenState,
    onSnackBarDismissed = { screenState.getScreenData(userAction = UserAction.Normal) },
    content = {
        ScreenContent(
            upcomingMovies = screenState.homeContent.upcomingMovies,
            popularMovies = screenState.homeContent.popularMovies,
            onSeeAllPopularMoviesClicked = screenState::onSeeAllPopularMoviesClicked,
            popularPeople = screenState.homeContent.popularPeople,
            onSeeAllPopularPeopleClicked = screenState::onSeeAllPopularPeopleClicked,
            nowPlayingMovies = screenState.homeContent.nowPlayingMovies,
            onSeeAllNowPlayingMoviesClicked = screenState::onSeeAllNowPlayingMoviesClicked,
            topRatedMovies = screenState.homeContent.topRatedMovies,
            onSeeAllTopRatedMoviesClicked = screenState::onSeeAllTopRatedMoviesClicked
        )
    }
)

@Composable
private fun ScreenContent(
    upcomingMovies: List<Movie>,
    popularMovies: List<ContentItem.Watchable>,
    onSeeAllPopularMoviesClicked: () -> Unit,
    popularPeople: List<ContentItem.Person>,
    onSeeAllPopularPeopleClicked: () -> Unit,
    nowPlayingMovies: List<ContentItem.Watchable>,
    onSeeAllNowPlayingMoviesClicked: () -> Unit,
    topRatedMovies: List<ContentItem.Watchable>,
    onSeeAllTopRatedMoviesClicked: () -> Unit
) {
    val listState: LazyListState = rememberLazyListState()
    val scope: CoroutineScope = rememberCoroutineScope()

    BackHandler(enabled = listState.firstVisibleItemScrollOffset > 0) {
        scope.launch {
            listState.scrollToItem(index = 0, scrollOffset = 0)
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.screenPadding),
        contentPadding = PaddingValues(bottom = AppTheme.dimens.screenPadding),
        state = listState
    ) {
        item {
            WatchablePager(
                pagerContent = upcomingMovies,
                onClick = { /*TODO: Implement it*/ },
                onPlayButtonClicked = { /*TODO: Implement it*/ },
                onAddToFavouriteButtonClicked = { /*TODO: Implement it*/ }
            )
        }
        item {
            WatchableWithRatingCarousel(
                header = stringResource(id = com.odogwudev.example.utils.R.string.popular_movies),
                buttonText = stringResource(id = com.odogwudev.example.utils.R.string.more_popular_movies),
                items = popularMovies,
                onItemClick = { /*TODO: Implement it*/ },
                onHeaderClick = onSeeAllPopularMoviesClicked,
            )
        }
        item {
            PeopleCarousel(
                header = stringResource(id = com.odogwudev.example.utils.R.string.popular_people),
                items = popularPeople,
                onItemClick = { /*TODO: Implement it*/ },
                onHeaderClick = onSeeAllPopularPeopleClicked,
            )
        }
        item {
            WatchableWithRatingCarousel(
                header = stringResource(id = com.odogwudev.example.utils.R.string.now_playing_movies),
                buttonText = stringResource(id = com.odogwudev.example.utils.R.string.more_now_playing_movies),
                items = nowPlayingMovies,
                onItemClick = { /*TODO: Implement it*/ },
                onHeaderClick = onSeeAllNowPlayingMoviesClicked,
            )
        }
        item {
            WatchableWithRatingCarousel(
                header = stringResource(id = com.odogwudev.example.utils.R.string.top_rated_movies),
                buttonText = stringResource(id = com.odogwudev.example.utils.R.string.more_top_rated_movies),
                items = topRatedMovies,
                onItemClick = { /*TODO: Implement it*/ },
                onHeaderClick = onSeeAllTopRatedMoviesClicked,
                showDivider = false
            )
        }
    }
}