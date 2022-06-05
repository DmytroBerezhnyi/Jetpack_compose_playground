package com.example.playground.compose.ui.air_conditioner.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AnimateVisibilityFromBottomToTop(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = Modifier,
        enter = expandVertically(animationSpec = tween(durationMillis = 200)) { fullWidth ->
            -fullWidth / 3
        } + fadeIn(animationSpec = tween(durationMillis = 200)),
        exit = slideOutVertically(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
            -100
        } + fadeOut(),
        content = content
    )
}

@Composable
fun AnimateVisibilityFromTopToBottom(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = Modifier,
        enter = slideInVertically(animationSpec = tween(durationMillis = 200)) { fullWidth ->
            -fullWidth / 3
        } + fadeIn(animationSpec = tween(durationMillis = 200)),
        exit = slideOutVertically(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
            100
        } + fadeOut(),
        content = content
    )
}