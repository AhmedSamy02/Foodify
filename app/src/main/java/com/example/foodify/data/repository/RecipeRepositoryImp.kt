package com.example.foodify.data.repository

import com.example.foodify.data.local.CollectionRecipeCrossRef
import com.example.foodify.data.local.Recipe
import com.example.foodify.data.database.RecipeDao
import com.example.foodify.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImp @Inject constructor(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun addRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }

    override fun searchRecipes(
        query: String,
        tags: List<String>
    ): Flow<List<Recipe>> {
        return recipeDao.searchRecipes(query)
    }

    override suspend fun getRecipeById(id: String): Recipe? {
        return recipeDao.getRecipeById(id)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }
//    private fun Recipe.toEntity(): Recipe{
//        return Recipe(
//            id = id,
//            title = title,
//            imageUrl = imageUrl,
//            ingredients = ingredients.joinToString(","),
//            steps = steps.joinToString(","),
//            tags = tags.joinToString(",")
//        )
//    }
//
//    private fun Recipe.toDomain(): Recipe {
//        return Recipe(
//            id = id,
//            title = title,
//            imageUrl = imageUrl,
//            ingredients = ingredients.split(","),
//            steps = steps.split(","),
//            tags = tags.split(",") // Convert string to list
//        )
//    }

}