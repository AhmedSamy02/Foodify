package com.example.foodify.ui.screens.onboarding

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foodify.ui.navigation.Screen
import com.example.foodify.utils.aqradaFontFamily


@Composable
fun OnBoardingContainer(
    color: Color,
    text: String,
    progress: Float,
    showButton: Boolean,
    navController: NavHostController
) {
    val animateRadius by rememberInfiniteTransition().animateFloat(
        initialValue = 80f,
        targetValue = 60f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(156.dp)
                .background(Color.Black)
                .padding(16.dp)
        ) {
            Text(
                text,
                fontFamily = aqradaFontFamily,
                fontSize = 22.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )

        }
        Box(
            Modifier
                .size(70.dp)
                .background(color, CircleShape)
                .align(Alignment.BottomCenter), contentAlignment = Alignment.Center
        ) {
            if (!showButton) TimerProgress(
                progress,
                strokeWidth = 2.dp,
                size = 60.dp,
            ) else {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Canvas(Modifier.size(70.dp)) {
                        drawCircle(Color.White, radius = animateRadius, style = Stroke(3f))
                    }
                    Button(
                        onClick = {
                            navController.navigate(Screen.Home.route) {
                                navController.popBackStack()
                            }
                        },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        contentPadding = ButtonDefaults.TextButtonContentPadding,
                        modifier = Modifier
                            .size(50.dp)
                    ) {
                        Text("Go", fontSize = 14.sp)
                    }
                }


            }
        }
    }


}
