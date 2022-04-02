package com.example.viewpagercompose


import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCircularProgress(
    canvasSize: Dp = 100.dp,
    indicatorValue: Int = 0,
    backgroundIndicatorColor: Color = Color.White,
    backgroundIndicatorStrokeWidth: Float = 4f,
    foregroundIndicatorColor: Color = Color.Black,
    foregroundIndicatorStrokeWidth: Float = 4f,
) {

    val animatedIndicatorValue = remember {
        Animatable(initialValue = 0f)
    }
    LaunchedEffect(key1 = indicatorValue, block = {
        animatedIndicatorValue.animateTo(indicatorValue.toFloat())
    })
    val sweepAngle by animateFloatAsState(
        targetValue = animatedIndicatorValue.value,
        animationSpec = tween(250)
    )
    val fabSize = animateDpAsState(
        targetValue = if (indicatorValue != 360) 60.dp else 110.dp,
        animationSpec = if (indicatorValue != 360)
            spring(Spring.DampingRatioHighBouncy)
        else
            spring(Spring.DampingRatioNoBouncy)
    )
    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                backgroundIndicator(
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth
                )
            }, verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(fabSize.value),
            backgroundColor = Color.Black
        ) {
            if (indicatorValue != 360) {
                Text(text = "NEXT", color = Color.White)
            } else {
                Text(text = "START", color = Color.White)
            }
        }
    }
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float = 0f,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        color = indicatorColor,
        startAngle = 90f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        )
    )
}

fun DrawScope.backgroundIndicator(
    sweepAngle: Float = 360f,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        color = indicatorColor,
        startAngle = 90f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CustomCircularProgressPreview() {
    CustomCircularProgress()
}

