package com.example.foodify.presentation.screens.add_recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodify.presentation.components.ValidatedOutlinedTextField
import com.example.foodify.presentation.theme.AddRecipeContainerTitleColor

@Composable
fun AddStepField(
    onAddStep: (String) -> Unit = {}
){
    var text by remember { mutableStateOf("") }
    val ingredientValidator: (String) -> String? = { input ->
        if (input.isBlank()) {
            "Steps cannot be empty"
        } else {
            null
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (ingredientValidator(text) == null) {
                    onAddStep(text)
                    text = ""
                }
            },
            modifier = Modifier.padding(end = 8.dp).size(64.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = AddRecipeContainerTitleColor
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
                tint = Color.White,
            )
        }
        ValidatedOutlinedTextField(
            onValueChange = {
                text = it
            },
            validator = ingredientValidator,
            placeHolderText = "Add a new Step...",
            modifier = Modifier.padding(0.dp)
        )

    }
}