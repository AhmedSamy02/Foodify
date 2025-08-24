package com.example.foodify.domain.repository

import com.example.foodify.data.local.CollectionEntity
import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.domain.model.Collection
import com.example.foodify.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    suspend fun createCollection(collection: Collection)
    suspend fun renameCollection(collectionId: Int, newName: String)
    suspend fun deleteCollection(collectionId: Int)
    suspend fun addRecipeToCollection(collectionId: Int, recipeId: Int)
    suspend fun removeRecipeFromCollection(collectionId: Int, recipeId: Int)
    fun getCollections(): Flow<List<Collection>>
    fun getRecipesInCollection(collectionId: Int): Flow<List<Recipe>>
}
