package com.anlian.alurmo.ui.view

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    initialValue: Float,
    targetValue: Float,
    size: Dp,
    color: Color = MaterialTheme.colorScheme.secondary,
    strokeWidth: Dp = 1.dp
) {
    val infiniteTransition = rememberInfiniteTransition()
    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(animation = tween(100))
    )
    CircularProgressIndicator(
        progress = progressAnimationValue,
        modifier = Modifier.size(size),
        color = color,
        strokeWidth = strokeWidth
    )
}