package com.odogwudev.example.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.barabasizsolt.util.ImageType
import com.barabasizsolt.util.withShadow
import com.odogwudev.example.domain_model.ContentItem
import com.odogwudev.example.theme.AppTheme
import com.odogwudev.example.utils.getImageKey

@Composable
fun MediumPersonCard(
    modifier: Modifier = Modifier,
    item: ContentItem.Person,
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
    Text(
        text = item.name,
        style = AppTheme.typography.body2.withShadow(),
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .align(alignment = Alignment.BottomCenter)
            .padding(all = AppTheme.dimens.contentPadding + AppTheme.dimens.smallPadding)
    )
}

@Composable
fun PersonCard(
    modifier: Modifier = Modifier,
    person: ContentItem.Person,
    onClick: () -> Unit
) = Row(
    modifier = modifier.clickable { onClick() },
    horizontalArrangement = Arrangement.spacedBy(space = AppTheme.dimens.contentPadding),
    verticalAlignment = Alignment.CenterVertically
) {
    val names = person.name.split(" ", limit = 2)

    MovaImage(
        imageUrl = person.posterPath.getImageKey(imageType = ImageType.ORIGINAL),
        modifier = Modifier
            .size(size = 80.dp)
            .clip(shape = CircleShape),
        contentScale = ContentScale.Crop
    )
    Column {
        NameText(name = names[0])
        NameText(name = names[1])
    }
}

@Composable
private fun NameText(
    modifier: Modifier = Modifier,
    name: String
) = Text(
    modifier = modifier,
    text = name,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
    style = AppTheme.typography.body1,
    fontWeight = FontWeight.Bold,
    color = AppTheme.colors.onPrimary
)