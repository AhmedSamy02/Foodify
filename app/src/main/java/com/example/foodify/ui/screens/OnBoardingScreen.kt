package com.example.foodify.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    Scaffold(Modifier.fillMaxSize()) { innerPadding ->
        OnBoardingContainer()
    }
}
@Composable
fun OnBoardingContainer(){

}