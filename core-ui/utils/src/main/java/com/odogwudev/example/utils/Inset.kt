package com.odogwudev.example.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

val navigationBarInsetDp: Dp
    @Composable
    get() = with(LocalDensity.current) {
        WindowInsets.navigationBars.getBottom(density = this).toDp()
    }

val statusBarInsetDp: Dp
    @Composable
    get() = with(LocalDensity.current) {
        WindowInsets.statusBars.getTop(density = this).toDp()
    }

val imeBottomInsetDp: Dp
    @Composable
    get() = with(LocalDensity.current) {
        WindowInsets.ime.getBottom(density = this).toDp()
    }