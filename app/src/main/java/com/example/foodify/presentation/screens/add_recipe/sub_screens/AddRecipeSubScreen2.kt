package com.example.foodify.presentation.screens.add_recipe.sub_screens

import AddIngredientField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodify.presentation.components.IndeterminateCircularIndicator
import com.example.foodify.presentation.screens.add_recipe.components.SelectorCard
import com.example.foodify.presentation.screens.add_recipe.components.SelectorCardLabel
import com.example.foodify.presentation.viewmodels.AddRecipeViewModel

@Composable
fun AddRecipeSubScreen2(viewModel: AddRecipeViewModel) {
    Column(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
        ) {
            itemsIndexed(viewModel.state.ingredients) { index, ingredient ->
                Box(
                    Modifier
                        .fillMaxWidth()
                ) {
                    SelectorCard(color = Color.Transparent) {
                        Text(
                            ingredient,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 24.dp)
                        )
                    }
                    SelectorCardLabel((index + 1).toString())
                }
            }
        }
        AddIngredientField {
            viewModel.addIngredients(it)
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { viewModel.validateScreen() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Next")
        }
        if(viewModel.state.errorMessage.isNotBlank())Text(
            viewModel.state.errorMessage,
            color = Color.Red,
            modifier = Modifier.padding(bottom = 36.dp, start = 36.dp, end = 36.dp)
        )
//        if (viewModel.state.isLoading) IndeterminateCircularIndicator()
    }

}