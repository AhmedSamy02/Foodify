package com.example.foodify.data.repository

import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.data.database.RecipeDao
import com.example.foodify.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImp @Inject constructor(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun addRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    override fun searchRecipes(
        query: String,
        tags: List<String>
    ): Flow<List<RecipeEntity>> {
        return recipeDao.searchRecipes(query)
    }

    override suspend fun getRecipeById(id: String): RecipeEntity? {
        return recipeDao.getRecipeById(id)
    }

    override suspend fun deleteRecipe(recipe: RecipeEntity) {
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