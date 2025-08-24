package com.example.foodify.domain.usecase

import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.domain.repository.RecipeRepository
import jakarta.inject.Inject

class AddRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeEntity): Result<Unit> {
        return try {
            repository.addRecipe(recipe)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}