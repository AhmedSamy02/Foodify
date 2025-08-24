package com.example.foodify.presentation.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.domain.usecase.AddRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddRecipeState(
    var name: String = "",
    var imageURL: String = "",
    var number: Int = 1,
    var cookTimeHours: Int = 0,
    var cookTimeMinutes: Int = 0,
    var difficulty: String = "Easy",
    var dishType: MutableList<String> = mutableListOf(),
    var suggestedDietaryTarget: MutableList<String> = mutableListOf(),
    var ingredients: MutableList<String> = mutableListOf(),
    var steps: MutableList<String> = mutableListOf(),
    var screenNum: Int = 0,
    var errorMessage: String = "",
    var isLoading: Boolean = false,
)

@HiltViewModel
class AddRecipeViewModel @Inject constructor(private val useCase: AddRecipeUseCase) : ViewModel() {
    var state by mutableStateOf(AddRecipeState())
        private set

    fun validateName(name: String): String? {
        if (name.isNotBlank()) {
            return null
        }
        return "Name can't be empty"
    }

    fun validateImageUrl(imageURL: String): String? {
        if (imageURL.isNotBlank()) {
            return null
        }
        return "Url can't be empty"
    }


    fun validateCookTimeHour(cookTimeHours: Int): String? {
        if (cookTimeHours >= 0) {
            return null
        }
        return "Hours can't be negative value"
    }

    fun validateCookTimeMinutes(cookTimeMinutes: Int): String? {
        if (cookTimeMinutes > 0 && state.cookTimeHours == 0 || cookTimeMinutes >= 0 && state.cookTimeHours > 0) {
            return null
        }
        return "Minutes can't be zero for zero hours"
    }

    fun addIngredients(ingredient: String) {
        if (ingredient.isNotBlank()) {
            state = state.copy(
                ingredients = state.ingredients.toMutableList().apply { add(ingredient) })
        }
    }

    fun addStep(step: String) {
        if (step.isNotBlank()) {
            state = state.copy(
                steps = state.steps.toMutableList().apply { add(step) })
        }
    }

    fun clearAll() {
        state = state.copy()
    }

    fun validateScreen(context: Context? = null) {
        when (state.screenNum) {
            0 -> {
                val errors = listOfNotNull(
                    validateName(state.name),
                    validateImageUrl(state.imageURL),
                    validateCookTimeHour(state.cookTimeHours),
                    validateCookTimeMinutes(state.cookTimeMinutes)
                )
                state = if (errors.isEmpty()) {
                    state.copy(
                        screenNum = state.screenNum + 1,
                        errorMessage = ""
                    )
                } else {
                    state.copy(
                        errorMessage = errors.joinToString(separator = "\n")
                    )
                }
            }

            1 -> {
                state = if (state.ingredients.isEmpty()) {
                    state.copy(errorMessage = "Please add at least one ingredient")
                } else {
                    state.copy(
                        screenNum = state.screenNum + 1,
                        errorMessage = ""
                    )
                }
            }

            else -> {
                if (state.steps.isEmpty()) {
                    state = state.copy(errorMessage = "Please add at least one step")
                } else {
                    state = state.copy(isLoading = true, errorMessage = "")
                    viewModelScope.launch {
                        val res = useCase.invoke(
                            RecipeEntity(
                                title = state.name,
                                imageUrl = state.imageURL,
                                ingredients = state.ingredients.toList(),
                                steps = state.steps.toList(),
                                difficulty = state.difficulty,
                                dishType = state.dishType[0],
                                cookTime = state.cookTimeMinutes + state.cookTimeHours * 60,
                            )
                        )
                        res.fold(onSuccess = { u ->
                            state = state.copy(
                                isLoading = false,
                                errorMessage = "Added Successfully"
                            )
                            delay(3000)
                            clearAll()
                        }, onFailure = {
                            state = state.copy(
                                isLoading = false,
                                errorMessage = "Error while adding : ${it.message}"
                            )

                        })

                    }
                }
            }
        }
    }
}