package com.example.foodify.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodify.presentation.theme.MainColor

@Composable
fun IndeterminateCircularIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.size(56.dp).fillMaxWidth(),
        color = MainColor,
        trackColor = Color.DarkGray,
    )
}