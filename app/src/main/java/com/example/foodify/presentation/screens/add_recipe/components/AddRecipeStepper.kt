package com.example.foodify.presentation.screens.add_recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddRecipeStepper(
    steps: List<String>,
    currentStep: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        steps.forEachIndexed { index, step ->
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        Color.DarkGray,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${index + 1}",
                    color = if (index < currentStep) Color.Yellow else Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (index + 1 == currentStep) {
                Box(
                    modifier = Modifier
                        .background(Color.DarkGray, RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = step,
                        color = Color.Yellow
                    )
                }
            }

            if (index != steps.lastIndex) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(2.dp)
                        .background(Color.DarkGray)
                )
            }
        }
    }
}