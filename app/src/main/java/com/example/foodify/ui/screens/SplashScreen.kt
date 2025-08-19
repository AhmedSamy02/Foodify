package com.example.foodify.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.foodify.ui.theme.FoodifyTheme

@Composable
fun SplashScreen(){
    Scaffold(modifier = Modifier.fillMaxSize()){ innerPadding ->
        Greeting("Ahmed", Modifier.padding(innerPadding))
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GreetingPreview() {
    FoodifyTheme {
        Greeting("Android")
    }
}