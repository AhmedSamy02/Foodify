package com.example.foodify.presentation.screens.add_recipe.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodify.presentation.theme.MainColor
import com.example.foodify.presentation.viewmodels.AddRecipeViewModel
import com.example.foodify.utils.aqradaFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeAppBar(viewModel: AddRecipeViewModel) {
    TopAppBar(
        modifier = Modifier.clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MainColor),
        title = {
            Text(
                "New Recipe",
                fontFamily = aqradaFontFamily,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            )
        },
//        navigationIcon = {
//            IconButton(onClick = { /* Handle navigation */ }) {
//                Icon(
//                    Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = "Menu",
//                    tint = Color.White
//                )
//            }
//        },
        actions = {
            Button(
                onClick = { viewModel.clearAll() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    "Clear all",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
            }
        }
    )

}