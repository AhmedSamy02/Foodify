package com.example.foodify.ui.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodify.ui.navigation.Screen
import com.example.foodify.ui.theme.LoadingCircle
import kotlinx.coroutines.delay


@Composable
fun CirclesLoader(modifier: Modifier, circleSize: Dp,navController: NavController) {
    var startFill1 by remember { mutableStateOf(false) }
    var startFill2 by remember { mutableStateOf(false) }
    var startFill3 by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(500)
        startFill1 = true
        delay(800)
        startFill2 = true
        delay(800)
        startFill3 = true
        delay(1000)
        navController.navigate(Screen.OnBoarding.route){
            navController.popBackStack()
        }
    }
    val c1 by animateFloatAsState(
        targetValue = if (startFill1) 1f else 0f,
        animationSpec = tween(durationMillis = 800),

        )
    val c2 by animateFloatAsState(
        targetValue = if (startFill2) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
    )

    val c3 by animateFloatAsState(
        targetValue = if (startFill3) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
    )

    Row(modifier, horizontalArrangement = Arrangement.Center) {
        Canvas(Modifier.size(circleSize)) {
            drawCircle(color = LoadingCircle, style = Stroke(width = 2.0f))
            drawCircle(
                color = LoadingCircle,
                style = Fill,
                alpha = c1
            )
        }
        Canvas(
            Modifier
                .padding(horizontal = 16.dp)
                .size(circleSize)
        ) {
            drawCircle(color = LoadingCircle, style = Stroke(width = 2.0f))
            drawCircle(
                color = LoadingCircle,
                style = Fill,
                alpha = c2,
            )
        }
        Canvas(
            Modifier
                .size(circleSize)
        ) {
            drawCircle(color = LoadingCircle, style = Stroke(width = 2.0f))
            drawCircle(
                color = LoadingCircle,
                style = Fill,
                alpha = c3
            )
        }
    }
}