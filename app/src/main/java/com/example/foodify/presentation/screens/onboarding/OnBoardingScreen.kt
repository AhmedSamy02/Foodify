package com.example.foodify.presentation.screens.onboarding

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodify.presentation.screens.onboarding.OnBoardingViewModel

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    val viewModel: OnBoardingViewModel = viewModel()
    var start by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        if (start) 1f else 0f,
        animationSpec = tween(
            durationMillis = 9700, easing = LinearEasing,
        )
    )
    LaunchedEffect(Unit) {
        start = true
    }

    Scaffold(Modifier.fillMaxSize(), containerColor = viewModel.color.value) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (viewModel.imagesList.value.isEmpty()) {
                    Box(
                        Modifier
                            .padding(bottom = 18.dp)
                            .size(120.dp)
                    )
                    Box(
                        Modifier
                            .padding(bottom = 18.dp)
                            .size(120.dp)
                    )
                    Box(
                        Modifier
                            .padding(bottom = 18.dp)
                            .size(120.dp)
                    )
                } else {
                    viewModel.imagesList.value.forEachIndexed { index, id ->
                        OnBoardingImageItem(id, 18.dp, index * 500)
                    }
                }

                Card(
                    modifier = Modifier.size(312.dp, 196.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = viewModel.color.value)
                ) {
                    OnBoardingContainer(
                        viewModel.color.value,
                        viewModel.text.value,
                        progress,
                        viewModel.showButton.value,
                        navController
                    )
                }

            }
        }
    }

}
