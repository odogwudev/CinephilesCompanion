package com.odogwudev.example.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.odogwudev.example.domain_model.ContentItem
import com.odogwudev.example.theme.AppTheme

@Composable
fun WatchableWithRatingCarousel(
    modifier: Modifier = Modifier,
    header: String,
    buttonText: String? = null,
    showDivider: Boolean = true,
    items: List<ContentItem.Watchable>,
    onItemClick: (String) -> Unit,
    onHeaderClick: () -> Unit,
) = CardCarousel(
    header = header,
    onHeaderClick = onHeaderClick,
    buttonText = buttonText,
    showDivider = showDivider,
    content = {
        items(items = items) { item ->
            WatchableWithRating(
                item = item,
                onClick = { onItemClick(item.id) },
                modifier = Modifier.height(height = AppTheme.dimens.watchableCardHeight)
            )
        }
    },
    modifier = modifier
)

@Composable
fun PeopleCarousel(
    modifier: Modifier = Modifier,
    header: String,
    buttonText: String? = null,
    items: List<ContentItem>,
    onItemClick: (String) -> Unit,
    onHeaderClick: () -> Unit,
) = CardCarousel(
    header = header,
    onHeaderClick = onHeaderClick,
    buttonText = buttonText,
    itemSpacing = AppTheme.dimens.screenPadding,
    showDivider = true,
    content = {
        items(items = items) { item ->
            PersonCard(
                person = item as ContentItem.Person,
                onClick = { onItemClick(item.id) }
            )
        }
    },
    modifier = modifier
)

@Composable
private fun CardCarousel(
    modifier: Modifier = Modifier,
    header: String,
    buttonText: String? = null,
    showDivider: Boolean,
    onHeaderClick: () -> Unit,
    itemSpacing: Dp = AppTheme.dimens.contentPadding,
    content: LazyListScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.screenPadding)
    ) {
        CardCarouselHeader(
            header = header,
            onHeaderClick = onHeaderClick,
            modifier = Modifier.padding(horizontal = AppTheme.dimens.screenPadding)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = AppTheme.dimens.screenPadding),
            horizontalArrangement = Arrangement.spacedBy(space = itemSpacing)
        ) {
            content()
        }
        if (buttonText != null) {
            MovaButton(
                text = buttonText,
                onClick = onHeaderClick,
                modifier = Modifier.padding(horizontal = AppTheme.dimens.screenPadding)
            )
        }
        if (showDivider) {
            Divider(
                color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                modifier = Modifier.padding(horizontal = AppTheme.dimens.screenPadding)
            )
        }
    }
}

@Composable
private fun CardCarouselHeader(
    modifier: Modifier = Modifier,
    header: String,
    onHeaderClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = header,
            style = AppTheme.typography.h6,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(weight = 1f)
        )
        Box(modifier = Modifier.clickable { onHeaderClick() }) {
            Text(
                text = "See all",
                color = AppTheme.colors.secondary,
                style = AppTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(all = AppTheme.dimens.smallPadding)
            )
        }
    }
}