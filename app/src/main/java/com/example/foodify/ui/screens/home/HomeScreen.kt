package com.example.foodify.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.foodify.ui.screens.onboarding.OnBoardingContainer

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(Modifier.fillMaxSize()) { innerPadding ->
        HomeScreenContainer(Modifier
            .fillMaxSize()
            .padding(innerPadding))
    }
}

@Composable
fun HomeScreenContainer(modifier: Modifier) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Home Screen", color = Color.White)
    }
}