package com.example.foodify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodify.presentation.navigation.CustomBottomBar
import com.example.foodify.presentation.navigation.NavGraph
import com.example.foodify.presentation.navigation.Screen
import com.example.foodify.presentation.theme.FoodifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            FoodifyTheme {
                Scaffold(
                    bottomBar = {
                        if (currentRoute != Screen.Splash.route && currentRoute != Screen.OnBoarding.route) {
                            CustomBottomBar(navController)
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        startDestination = Screen.Splash.route
                    )
                }
            }
        }
    }
}
