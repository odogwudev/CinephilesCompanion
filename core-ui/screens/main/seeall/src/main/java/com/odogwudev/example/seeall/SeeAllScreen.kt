package com.odogwudev.example.seeall

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.odogwudev.example.base.BaseScreen
import com.odogwudev.example.base.UserAction
import com.odogwudev.example.catalog.*
import com.odogwudev.example.domain_model.ContentItem
import com.odogwudev.example.domain_usecase.screen.listall.SeeAllContentType
import com.odogwudev.example.theme.AppTheme
import com.odogwudev.example.utils.R
import com.odogwudev.example.utils.navigationBarInsetDp
import com.odogwudev.example.utils.statusBarInsetDp

@Composable
fun SeeAllScreen(screenState: SeeAllScreenState) = BaseScreen(
    screenState = screenState,
    onSnackBarDismissed = { screenState.getScreenData(userAction = UserAction.Normal) },
    snackBarModifier = Modifier.systemBarsPadding(),
    content = {
        ScreenContent(
            items = screenState.watchableItems,
            onLoadMoreItem = { screenState.getScreenData(userAction = UserAction.Normal) },
            onRetryClick = { screenState.getScreenData(userAction = UserAction.Normal) },
            onUpClicked = screenState::onUpClicked,
            contentType = screenState.contentType
        )
    }
)

@Composable
private fun ScreenContent(
    items: List<ContentItem>,
    onLoadMoreItem: () -> Unit,
    onRetryClick: () -> Unit,
    onUpClicked: () -> Unit,
    contentType: String,
) {
    val gridState: LazyGridState = rememberLazyGridState()

    ScrollUpWrapper(
        gridState = gridState,
        content = {
            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Fixed(count = 6),
                verticalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.contentPadding),
                horizontalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.contentPadding),
                contentPadding = PaddingValues(
                    start = AppTheme.dimens.screenPadding,
                    end = AppTheme.dimens.screenPadding,
                    bottom = AppTheme.dimens.screenPadding + navigationBarInsetDp,
                    top = AppTheme.dimens.screenPadding + statusBarInsetDp
                )
            ) {
                header(contentType = contentType, onClick = onUpClicked)
                content(items = items, onLoadMoreItem = onLoadMoreItem, onRetryClick = onRetryClick)
            }
        }
    )
}

private fun LazyGridScope.header(
    modifier: Modifier = Modifier,
    contentType: String,
    onClick: () -> Unit
) = item(span = { GridItemSpan(currentLineSpan = 6) }) {
    MovaHeader(
        text = when (contentType) {
            SeeAllContentType.POPULAR_MOVIES.name -> stringResource(id = R.string.popular_movies)
            SeeAllContentType.POPULAR_PEOPLE.name -> stringResource(id = R.string.popular_people)
            SeeAllContentType.NOW_PLAYING_MOVIES.name -> stringResource(id = R.string.now_playing_movies)
            SeeAllContentType.TOP_RATED_MOVIES.name -> stringResource(id = R.string.top_rated_movies)
            else -> stringResource(id = R.string.all_content)
        },
        onClick = onClick,
        modifier = modifier.padding(bottom = AppTheme.dimens.contentPadding)
    )
}

private fun LazyGridScope.content(
    items: List<ContentItem>,
    onLoadMoreItem: () -> Unit,
    onRetryClick: () -> Unit
) = itemsIndexed(
    items = items,
    key = { index, item -> item.id + index },
    span = { _, item ->
        GridItemSpan(
            currentLineSpan = when (item) {
                is ContentItem.ItemTail, is ContentItem.ItemError -> 6
                is ContentItem.Person -> 2
                else -> 3
            }
        )
    }
) { _, item ->
    when (item) {
        is ContentItem.Watchable -> WatchableWithRating(
            item = item,
            onClick = { /*TODO: Implement it*/ }
        )
        is ContentItem.Person -> MediumPersonCard(
            item = item,
            onClick = { /*TODO: Implement it*/ }
        )
        is ContentItem.ItemTail -> if (item.loadMore) {
            LoadingContent(modifier = Modifier
                .height(height = 80.dp)
                .fillMaxWidth())
            SideEffect { onLoadMoreItem() }
        }
        is ContentItem.ItemError -> ErrorItem(onRetryClick = onRetryClick)
    }
}