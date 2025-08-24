package com.example.foodify.presentation.screens.add_recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foodify.presentation.screens.add_recipe.components.AddRecipeAppBar
import com.example.foodify.presentation.screens.add_recipe.components.AddRecipeStepper
import com.example.foodify.presentation.screens.add_recipe.sub_screens.AddRecipeSubScreen1
import com.example.foodify.presentation.screens.add_recipe.sub_screens.AddRecipeSubScreen2
import com.example.foodify.presentation.screens.add_recipe.sub_screens.AddRecipeSubScreen3
import com.example.foodify.presentation.viewmodels.AddRecipeViewModel

@Composable
fun AddRecipeScreen(navController: NavHostController) {
    val viewModel: AddRecipeViewModel = hiltViewModel()
    val state = viewModel.state
    val focusManager = LocalFocusManager.current

    Scaffold(Modifier.fillMaxSize(), topBar = { AddRecipeAppBar(viewModel) }) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 120.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }

                ) {
                    focusManager.clearFocus()
                },
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddRecipeStepper(
                steps = listOf("Recipe Information", "Ingredients", "Steps"),
                currentStep = state.screenNum + 1
            )
            when (state.screenNum) {
                0 -> AddRecipeSubScreen1(viewModel)
                1 -> AddRecipeSubScreen2(viewModel)
                2 -> AddRecipeSubScreen3(viewModel)
            }
        }
    }
}



