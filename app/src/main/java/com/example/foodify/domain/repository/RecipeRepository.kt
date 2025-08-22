package com.example.foodify.domain.repository

import com.example.foodify.data.local.CollectionRecipeCrossRef
import com.example.foodify.data.local.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun addRecipe(recipe: Recipe)
    fun searchRecipes(query: String, tags: List<String>): Flow<List<Recipe>>
    suspend fun getRecipeById(id: String): Recipe?
    suspend fun deleteRecipe(recipe: Recipe)
}