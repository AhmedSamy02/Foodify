package com.example.foodify.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp

@Composable
fun ValidatedOutlinedTextField(
    onValueChange: (String) -> Unit,
    validator: (String) -> String?,
    placeHolderText: String,
    modifier: Modifier,
    suffixText: String? = null,
    keyboardType: KeyboardType= KeyboardType.Text
) {
    var text by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    OutlinedTextField(
        value = text,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        onValueChange = {
            text = it
            errorMessage = validator(it)
            onValueChange(it)
        },
        isError = errorMessage != null,
        supportingText = {
            if (errorMessage != null) {
                Text(text = errorMessage!!, color = Color.Red)
            }
        },
        modifier = modifier,
        placeholder = { Text(placeHolderText, fontWeight = FontWeight.Light) },
        suffix = {
            if (suffixText != null)
                Text(suffixText, fontSize = 10.sp) else null
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            errorBorderColor = Color.Red,
            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = Color.Transparent,
            errorTextColor = Color.Red,
            unfocusedTextColor = Color.LightGray,
            focusedTextColor = Color.DarkGray
        ),
        singleLine = true,

    )
}