package com.example.foodify.ui.screens.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    Scaffold(Modifier.fillMaxSize()) { innerPadding ->
        OnBoardingContainer()
    }
}
@Composable
fun OnBoardingContainer(){
    Text("Onboarding", color = Color.White)
}