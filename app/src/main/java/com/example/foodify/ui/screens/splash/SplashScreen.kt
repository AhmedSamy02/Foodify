package com.example.foodify.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodify.R
import com.example.foodify.ui.theme.MainColor

@Composable
fun SplashScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = MainColor) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            ) {
            Image(
                painterResource(R.mipmap.app_icon),
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp)
            )
            CirclesLoader(
                Modifier
                    .padding(top = 18.dp)
                    .fillMaxWidth(),
                16.dp,
                navController = navController,
            )
        }
    }
}
