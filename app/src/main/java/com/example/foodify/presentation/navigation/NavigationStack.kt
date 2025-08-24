package com.example.foodify.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodify.presentation.screens.collection.CollectionDetailScreen
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
        composable(Screen.Collections.route) {
            CollectionsScreen(navController)
        }
        composable(
            Screen.CollectionDetail.route,
            arguments = listOf(navArgument("collectionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val collectionId = backStackEntry.arguments?.getInt("collectionId") ?: 0
            CollectionDetailScreen(navController, collectionId)
        }
        composable(
            Screen.RecipeDetail.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
            RecipeDetailScreen(navController, recipeId)
        }
    }
}