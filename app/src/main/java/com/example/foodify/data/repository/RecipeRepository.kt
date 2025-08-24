package com.example.foodify.data.repository

import com.example.foodify.dao.RecipeDao
import com.example.foodify.datalocal.Recipe
import com.example.foodify.datalocal.RecipeWithDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface RecipeRepository {
    fun searchRecipes(
        title: String?,
        difficulty: String?,
        dishTypes: List<String>,
        maxCookingTime: Int?
    ): Flow<List<Recipe>>
}

