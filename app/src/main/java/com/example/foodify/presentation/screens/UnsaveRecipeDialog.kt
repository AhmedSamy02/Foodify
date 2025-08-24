package com.example.foodify.presentation.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun UnsaveRecipeDialog(
    recipeId: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Unsave Recipe") },
        text = { Text("Removing this recipe will also remove it from any collections.") },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Unsave") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
