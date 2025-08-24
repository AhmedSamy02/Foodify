package com.example.foodify.presentation.screens.add_recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodify.R
import com.example.foodify.presentation.theme.MainColor


@Composable
fun AddRecipeCounter(people: Int, onDecrement: () -> Unit, onIncrement: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SelectorCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MainColor)
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Serving for",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                IncrementDecrementButton(
                    iconId = R.drawable.ic_remove,
                    onClick = onDecrement
                )
                Text(
                    text = "$people",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                IncrementDecrementButton(
                    iconId = R.drawable.ic_add,
                    onClick = onIncrement
                )
                Text(
                    text = "People",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }        }
        SelectorCardLabel("Number")
    }

}

@Composable
fun IncrementDecrementButton(iconId: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(36.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null, // decorative icon
            tint = MainColor
        )
    }
}
