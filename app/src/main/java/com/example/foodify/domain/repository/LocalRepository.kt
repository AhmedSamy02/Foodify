package com.example.foodify.domain.repository

import com.example.foodify.data.models.CollectionRecipeCrossRef
import com.example.foodify.data.models.Recipe
import com.example.foodify.data.models.Collection

interface LocalRepository {
    suspend fun insertCollection(collection: Collection)
    suspend fun insertRecipe(recipe: Recipe)
    suspend fun insertCollectionRecipeCrossRef(collectionRecipeCrossRef: CollectionRecipeCrossRef)
    suspend fun getCollections(): List<Collection>
    suspend fun getRecipes(): List<Recipe>

}