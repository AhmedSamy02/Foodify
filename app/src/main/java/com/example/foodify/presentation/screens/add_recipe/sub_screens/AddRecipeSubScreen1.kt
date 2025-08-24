package com.example.foodify.presentation.screens.add_recipe.sub_screens

import AddIngredientField
import AddRecipeTime
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodify.presentation.components.IndeterminateCircularIndicator
import com.example.foodify.presentation.screens.add_recipe.components.AddRecipeCounter
import com.example.foodify.presentation.screens.add_recipe.components.AddRecipeSelectorWithChip
import com.example.foodify.presentation.screens.add_recipe.components.AddRecipeSelectorWithChipMultiple
import com.example.foodify.presentation.screens.add_recipe.components.AddRecipeSelectorWithTF
import com.example.foodify.presentation.viewmodels.AddRecipeViewModel
import com.example.foodify.utils.difficultyList
import com.example.foodify.utils.dishTypeList
import com.example.foodify.utils.suggestedDietaryTargetList

@Composable
fun AddRecipeSubScreen1(viewModel: AddRecipeViewModel) {
    var people by remember { mutableIntStateOf(1) }
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        AddRecipeSelectorWithTF(
            "Name",
            {
                viewModel.state.name = it
            },
            placeHolderText = "Name your recipe",
            validator = { return@AddRecipeSelectorWithTF viewModel.validateName(it) })
        AddRecipeSelectorWithTF(
            "Image URL",
            {
                viewModel.state.imageURL = it
            },
            placeHolderText = "ImageURL",
            validator = { return@AddRecipeSelectorWithTF viewModel.validateImageUrl(it) })
        AddRecipeCounter(people, onDecrement = {
            if (people > 1) {
                people--
                viewModel.state.number = people
            }
        }, onIncrement = {
            if (people < 10) {
                people++
                viewModel.state.number = people
            }
        })
        AddRecipeTime(
            onHourChange = {
                viewModel.state.cookTimeHours = if (it.isBlank()) 0 else it.toInt()
            },
            onMinuteChange = {
                viewModel.state.cookTimeMinutes = if (it.isBlank()) 0 else it.toInt()
            },
            hourValidator = {
                return@AddRecipeTime viewModel.validateCookTimeHour(if (it.isBlank()) 0 else it.toInt())
            },
            minuteValidator = {
                return@AddRecipeTime viewModel.validateCookTimeMinutes(if (it.isBlank()) 0 else it.toInt())

            }
        )
        AddRecipeSelectorWithChip(
            list = difficultyList,
            title = "Difficulty",
            onItemSelected = { index ->
                viewModel.state.difficulty = difficultyList[index]
            }
        )
        AddRecipeSelectorWithChipMultiple(viewModel.state.dishType, "Dish Type", dishTypeList)
        AddRecipeSelectorWithChipMultiple(
            viewModel.state.suggestedDietaryTarget, "Suggested Dietary Target",
            suggestedDietaryTargetList
        )
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
    }
}