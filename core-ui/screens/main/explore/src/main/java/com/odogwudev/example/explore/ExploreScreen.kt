package com.odogwudev.example.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.odogwudev.example.base.BaseScreen
import com.odogwudev.example.base.BaseScreenState
import com.odogwudev.example.base.UserAction
import com.odogwudev.example.catalog.*
import com.odogwudev.example.domain_model.ContentItem
import com.odogwudev.example.theme.AppTheme
import com.odogwudev.example.utils.imeBottomInsetDp
import com.odogwudev.example.utils.statusBarInsetDp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExploreScreen(screenState: ExploreScreenState) = BaseScreen(
    screenState = screenState,
    content = {
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
        val scope = rememberCoroutineScope()
        val gridState: LazyGridState = rememberLazyGridState()

        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetShape = AppTheme.shapes.medium.copy(
                bottomStart = CornerSize(size = 0.dp),
                bottomEnd = CornerSize(size = 0.dp)
            ),
            sheetContent = { FilterScreen() },
            sheetPeekHeight = 0.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = AppTheme.colors.primary)
            ) {
                ScreenContent(
                    query = screenState.query,
                    onQueryChange = screenState::onQueryChange,
                    discoverItems = screenState.discoverContent,
                    searchItems = screenState.searchContent,
                    isLoading = screenState.state in listOf(BaseScreenState.State.SwipeRefresh, BaseScreenState.State.Search),
                    bottomSheetScaffoldState = bottomSheetScaffoldState,
                    onLoadMoreItem = { screenState.getScreenData(userAction = UserAction.Normal) },
                    onRetryClick = {
                        screenState.getScreenData(
                            userAction = if (screenState.query.isNotEmpty() && screenState.searchContent.size <= 1) {
                                screenState.clearSearchContent()
                                UserAction.Search
                            } else {
                                UserAction.Normal
                            }
                        )
                    },
                    scope = scope,
                    gridState = gridState
                )
            }
        }
    }
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ScreenContent(
    query: String,
    onQueryChange: (String) -> Unit,
    discoverItems: List<ContentItem>,
    searchItems: List<ContentItem>,
    isLoading: Boolean,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    onLoadMoreItem: () -> Unit,
    onRetryClick: () -> Unit,
    scope: CoroutineScope,
    gridState: LazyGridState
) {
    Column(verticalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.screenPadding)) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange ,
            scope = scope,
            bottomSheetScaffoldState = bottomSheetScaffoldState
        )
        if (isLoading) {
            LoadingBody(
                gridState = gridState
            )
        } else {
            ContentBody(
                gridState = gridState,
                query = query,
                discoverItems = discoverItems,
                searchItems = searchItems,
                onLoadMoreItem = onLoadMoreItem,
                onRetryClick = onRetryClick
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    scope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) = Row(
    modifier = modifier
        .padding(
            top = statusBarInsetDp + AppTheme.dimens.screenPadding,
            start = AppTheme.dimens.screenPadding,
            end = AppTheme.dimens.screenPadding
        ),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.contentPadding)
) {
    MovaSearchField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.weight(weight = 1f)
    )
    FilterIcon(
        onClick = {
            scope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            }
        }
    )
}

@Composable
private fun ContentBody(
    modifier: Modifier = Modifier,
    gridState: LazyGridState,
    query: String,
    discoverItems: List<ContentItem>,
    searchItems: List<ContentItem>,
    onLoadMoreItem: () -> Unit,
    onRetryClick: () -> Unit
) = ScrollUpWrapper(
    gridState = gridState,
    content = {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.contentPadding),
            horizontalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.contentPadding),
            contentPadding = PaddingValues(
                start = AppTheme.dimens.screenPadding,
                end = AppTheme.dimens.screenPadding,
                bottom = AppTheme.dimens.screenPadding + imeBottomInsetDp
            ),
            modifier = modifier.fillMaxSize(),
            state = gridState
        ) {
            if (query.isNotEmpty()) {
                searchableItemsIndexed(
                    items = searchItems,
                    key = { index, item -> item.id + index },
                    span = searchableItemSpan(baseLineSpan = 2),
                    onLoadMoreItem = onLoadMoreItem,
                    onRetryClick = onRetryClick
                ) { _, item ->
                    SearchableItem(
                        item = item as ContentItem.Watchable,
                        onClick = { /*TODO: Implement it*/ }
                    )
                }
            } else {
                searchableItemsIndexed(
                    items = discoverItems,
                    key = { index, item -> item.id + index },
                    span = searchableItemSpan(baseLineSpan = 1),
                    onLoadMoreItem = onLoadMoreItem,
                    onRetryClick = onRetryClick
                ) { _, item ->
                    WatchableWithRating(
                        item = item as ContentItem.Watchable,
                        onClick = { /*TODO: Implement it*/ }
                    )
                }
            }
        }
    }
)

@Composable
private fun LoadingBody(gridState: LazyGridState) {
    LaunchedEffect(
        key1 = Unit,
        block = { gridState.scrollToItem(index = 0, scrollOffset = 0) }
    )
    LoadingContent()
}

private inline fun LazyGridScope.searchableItemsIndexed(
    items: List<ContentItem>,
    crossinline onLoadMoreItem: () -> Unit,
    crossinline onRetryClick: () -> Unit,
    noinline key: ((index: Int, item: ContentItem) -> Any)? = null,
    noinline span: (LazyGridItemSpanScope.(index: Int, item: ContentItem) -> GridItemSpan)? = null,
    crossinline contentType: (index: Int, item: ContentItem) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyGridItemScope.(index: Int, item: ContentItem) -> Unit
) = itemsIndexed(
    items = items,
    key = key,
    span = span,
    contentType = contentType
) { index, item ->
    when (item) {
        is ContentItem.ItemTail -> when {
            item.loadMore -> {
                LoadingContent(modifier = Modifier
                    .height(height = 80.dp)
                    .fillMaxWidth())
                SideEffect { onLoadMoreItem() }
            }
            items.size == 1 -> NotFoundItem()
        }
        is ContentItem.ItemError -> ErrorItem(
            onRetryClick = { onRetryClick() }
        )
        else -> itemContent(index, item)
    }
}

private fun searchableItemSpan(baseLineSpan: Int): (LazyGridItemSpanScope.(index: Int, item: ContentItem) -> GridItemSpan) = { _, item ->
    GridItemSpan(
        currentLineSpan = when (item) {
            is ContentItem.ItemTail, is ContentItem.ItemError -> 6
            else -> baseLineSpan
        }
    )
}