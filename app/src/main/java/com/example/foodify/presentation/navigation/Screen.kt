package com.example.foodify.presentation.navigation

sealed class Screen(val route:String) {
    object Home: Screen("home_screen")
    object Splash:Screen("splash_screen")
    object OnBoarding: Screen("onboarding_screen")
}