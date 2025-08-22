package com.example.foodify.domain.repository

import com.example.foodify.data.local.Collection
import com.example.foodify.data.local.CollectionWithRecipes
import com.example.foodify.data.local.Recipe
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    suspend fun createCollection(collection: Collection)
    suspend fun renameCollection(collectionId: String, newName: String)
    suspend fun deleteCollection(collectionId: String)
    suspend fun addRecipeToCollection(collectionId: Int, recipeId: Int)
    suspend fun removeRecipeFromCollection(collectionId: Int, recipeId: Int)
    fun getCollections(): Flow<List<Collection>>
    fun getRecipesInCollection(collectionId: String): Flow<List<Recipe>>
}
