package com.example.foodify.presentation.screens.add_recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodify.presentation.theme.AddRecipeContainerTitleColor
import com.example.foodify.presentation.theme.MainColor

@Composable
fun SelectorCard(color: Color = MainColor,content:@Composable ()-> Unit,){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            content = { content() }
        )
    }
}
@Composable
fun SelectorCardLabel(label:String){
    Box(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(bottomEnd = 8.dp))
            .padding(
                5.dp,
            )
            .background(
                AddRecipeContainerTitleColor,
                RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}