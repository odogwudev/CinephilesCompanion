package com.odogwudev.example.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.odogwudev.example.movie.model.Movie
import com.odogwudev.example.theme.AppTheme
import com.odogwudev.example.utils.ImageType
import com.odogwudev.example.utils.getImageKey
import com.odogwudev.example.utils.movieGenres
import com.odogwudev.example.utils.withShadow
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WatchablePager(
    modifier: Modifier = Modifier,
    pagerContent: List<Movie>,
    onClick: () -> Unit,
    onPlayButtonClicked: () -> Unit,
    onAddToFavouriteButtonClicked: () -> Unit
) {
    val pagerState = rememberPagerState()
    LaunchedEffect(Unit) {
        while (true) {
            delay(timeMillis = 5000)
            if (pagerState.pageCount > 0) {
                pagerState.animateScrollToPage(page = (pagerState.currentPage + 1) % (pagerState.pageCount))
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            modifier = modifier.fillMaxWidth(),
            count = pagerContent.size,
            state = pagerState
        ) { page ->
            PagerItem(
                item = pagerContent[page],
                onClick = onClick,
                onPlayButtonClicked = onPlayButtonClicked,
                onAddToFavouriteButtonClicked = onAddToFavouriteButtonClicked
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = AppTheme.colors.secondary,
            inactiveColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
        )
    }
}

@Composable
private fun PagerItem(
    modifier: Modifier = Modifier,
    item: Movie,
    onClick: () -> Unit,
    onPlayButtonClicked: () -> Unit,
    onAddToFavouriteButtonClicked: () -> Unit
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }) {
        MovaImage(
            imageUrl = item.posterPath?.getImageKey(imageType = ImageType.ORIGINAL).orEmpty(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 0.7f)
        )
        GradientOverlay(
            maxHeightFraction = 0.5f,
            colors = listOf(
                Color.Transparent,
                Color.Transparent,
                AppTheme.colors.primary.copy(alpha = 0.8f),
                AppTheme.colors.primary
            )
        )
        PagerItemInfo(
            item = item,
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .padding(
                    start = AppTheme.dimens.screenPadding,
                    bottom = AppTheme.dimens.screenPadding
                ),
            onPlayButtonClicked = onPlayButtonClicked,
            onAddToFavouriteButtonClicked = onAddToFavouriteButtonClicked
        )
    }
}

@Composable
private fun PagerItemInfo(
    modifier: Modifier = Modifier,
    item: Movie,
    onPlayButtonClicked: () -> Unit,
    onAddToFavouriteButtonClicked: () -> Unit
) {
    val genresText = item.genreIds.joinToString(separator = " ??? ") { genre -> movieGenres[genre].orEmpty() }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.contentPadding)
    ) {
        Text(
            text = item.title,
            style = AppTheme.typography.h5.withShadow(),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = genresText,
            style = AppTheme.typography.body1.withShadow(),
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )
        PagerItemButtons(
            onPlayButtonClicked = onPlayButtonClicked,
            onAddToFavouriteButtonClicked = onAddToFavouriteButtonClicked
        )
    }
}

@Composable
private fun PagerItemButtons(
    modifier: Modifier = Modifier,
    onPlayButtonClicked: () -> Unit,
    onAddToFavouriteButtonClicked: () -> Unit
) = Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.contentPadding * 2)
) {
    MovaFilledButton(
        text = "Play",
        icon = Icons.Filled.PlayCircle,
        onClick = onPlayButtonClicked
    )
    MovaOutlinedButton(
        text = "Favourites",
        icon = Icons.Filled.Favorite,
        contentColor = AppTheme.colors.secondary,
        onClick = onAddToFavouriteButtonClicked
    )
}