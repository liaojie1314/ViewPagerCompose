package com.example.viewpagercompose


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IndicatorVertical(isSelected: Boolean) {
    val height = animateDpAsState(
        targetValue = if (isSelected) 30.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(modifier = Modifier
        .height(height.value)
        .width(10.dp)
        .clip(CircleShape)
        .background(if (isSelected) Color.Black else Color.LightGray))
}

