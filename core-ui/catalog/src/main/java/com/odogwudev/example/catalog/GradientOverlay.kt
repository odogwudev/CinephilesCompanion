package com.odogwudev.example.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BoxScope.GradientOverlay(
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.BottomCenter,
    maxHeightFraction: Float = 1f,
    colors: List<Color>
) = Box(modifier = modifier.matchParentSize()) {
    Spacer(
        modifier = Modifier
            .align(alignment = alignment)
            .fillMaxWidth()
            .fillMaxHeight(fraction = maxHeightFraction)
            .background(brush = Brush.verticalGradient(colors = colors))
    )
}