package com.example.foodify.data.repository

import com.example.foodify.dao.RecipeDao
import com.example.foodify.datalocal.Recipe
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl @Inject constructor(
    private val dao: RecipeDao
) : RecipeRepository {
    override fun searchRecipes(
        title: String?,
        difficulty: String?,
        dishTypes: List<String>,
        maxCookingTime: Int?
    ): Flow<List<Recipe>> {
        return dao.searchRecipes(title, difficulty, dishTypes, dishTypes.size, maxCookingTime)
    }
}
