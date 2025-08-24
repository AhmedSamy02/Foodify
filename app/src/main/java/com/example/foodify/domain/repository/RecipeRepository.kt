package com.example.foodify.domain.repository

import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.datalocal.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun addRecipe(recipe: RecipeEntity)
//    fun searchRecipes(query: String, tags: List<String>): Flow<List<RecipeEntity>>
    suspend fun getRecipeById(id: String): RecipeEntity?
    suspend fun deleteRecipe(recipe: RecipeEntity)
    fun searchRecipes(
        title: String?,
        difficulty: String?,
        dishTypes: List<String>,
        maxCookingTime: Int?
    ): Flow<List<RecipeEntity>>
}