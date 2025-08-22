package com.example.foodify.presentation.screens.onboarding

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun OnBoardingImageItem(image: Int, bottomPadding: Dp, delayTime: Int,) {
    Log.d("Id ","image = $image")
    var outerOffset by remember { mutableStateOf((-1000).dp) }
    val finalOffset by animateDpAsState(
        targetValue = outerOffset, label = "image$image",

        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = delayTime,
            easing = FastOutSlowInEasing
        ),
    )
    LaunchedEffect(image) {
        outerOffset = 0.dp
    }
    Image(
        painterResource(image),
        contentDescription = "Dish-$image",
        modifier = Modifier
            .offset(y = finalOffset)
            .padding(bottom = bottomPadding)
            .size(120.dp)
    )
}
