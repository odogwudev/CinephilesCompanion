package com.odogwudev.example.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.odogwudev.example.domain_model.ContentItem
import com.odogwudev.example.theme.AppTheme
import com.odogwudev.example.utils.ImageType
import com.odogwudev.example.utils.getImageKey

@Composable
fun WatchableWithRating(
    modifier: Modifier = Modifier,
    item: ContentItem.Watchable,
    aspectRatio: Float = 0.7f,
    onClick: () -> Unit
) = Box {
    MovaImage(
        imageUrl = item.posterPath.getImageKey(imageType = ImageType.ORIGINAL),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(shape = AppTheme.shapes.medium)
            .aspectRatio(ratio = aspectRatio)
            .clickable { onClick() }
    )
    RatingHolder(
        rating = item.voteAverage,
        modifier = Modifier
            .align(alignment = Alignment.TopStart)
            .padding(all = AppTheme.dimens.contentPadding + AppTheme.dimens.smallPadding)
    )
}

@Composable
private fun RatingHolder(
    modifier: Modifier = Modifier,
    rating: String
) {
    Box(
        modifier = modifier
            .clip(shape = AppTheme.shapes.small)
            .background(color = AppTheme.colors.secondary)
    ) {
        Text(
            text = rating,
            style = AppTheme.typography.overLine,
            fontWeight = FontWeight.Bold,
            color = AppTheme.colors.onSecondary,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(
                    vertical = AppTheme.dimens.smallPadding,
                    horizontal = AppTheme.dimens.contentPadding
                )
        )
    }
}