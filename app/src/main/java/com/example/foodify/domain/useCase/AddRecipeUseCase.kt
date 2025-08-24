package com.example.foodify.domain.useCase

import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.domain.repository.RecipeRepository
import javax.inject.Inject

class AddRecipeUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeEntity) {
        recipeRepository.addRecipe(recipe)
    }
}
