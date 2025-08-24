package com.example.foodify.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodify.presentation.screens.home.HomeScreen
import com.example.foodify.presentation.screens.onboarding.OnBoardingScreen

import com.example.foodify.presentation.screens.splash.SplashScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
    }


}