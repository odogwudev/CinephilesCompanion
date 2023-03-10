package com.odogwudev.example.catalog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.odogwudev.example.theme.AppTheme

@Composable
fun MovaFilledButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconSize: Dp = 24.dp,
    text: String? = null,
    backgroundColor: Color = AppTheme.colors.secondary,
    contentColor: Color = Color.White,
    textStyle: TextStyle = AppTheme.typography.subtitle1,
    onClick: () -> Unit
) = Button(
    modifier = modifier,
    onClick = onClick,
    shape = CircleShape,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor
    ),
    content = {
        ButtonContent(
            icon = icon,
            text = text.orEmpty(),
            contentColor = contentColor,
            textStyle = textStyle,
            iconSize = iconSize
        )
    }
)

@Composable
fun MovaOutlinedButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String,
    contentColor: Color = Color.White,
    textStyle: TextStyle = AppTheme.typography.subtitle1,
    onClick: () -> Unit
) = Button(
    modifier = modifier,
    onClick = onClick,
    shape = CircleShape,
    border = BorderStroke(
        color = contentColor,
        width = 2.dp
    ),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = AppTheme.colors.primary.copy(
            alpha = if (isSystemInDarkTheme()) 0f else 0.7f
        )
    ),
    content = {
        ButtonContent(
            icon = icon,
            text = text,
            contentColor = contentColor,
            textStyle = textStyle
        )
    }
)

@Composable
fun MovaButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = AppTheme.colors.secondary,
    contentColor: Color = Color.White,
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) = Button(
    modifier = modifier
        .height(height = AppTheme.dimens.buttonSize)
        .fillMaxWidth(),
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        disabledBackgroundColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    ),
    shape = CircleShape,
    enabled = isEnabled
) {
    if (isLoading) {
        CircularProgressIndicator(
            color = contentColor,
            modifier = Modifier.size(size = 36.dp)
        )
    } else {
        Text(
            text = text,
            color = contentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ButtonContent(
    icon: ImageVector?,
    text: String,
    contentColor: Color,
    textStyle: TextStyle,
    iconSize: Dp = 24.dp
) {
    if (icon != null) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(size = iconSize)
        )
    }
    Spacer(modifier = Modifier.width(width = AppTheme.dimens.contentPadding))
    Text(
        text = text,
        style = textStyle,
        color = contentColor
    )
}