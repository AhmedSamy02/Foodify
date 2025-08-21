package com.example.foodify.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.foodify.data.models.Collection
import com.example.foodify.data.models.CollectionRecipeCrossRef
import com.example.foodify.data.models.Recipe

@Dao
interface DAO {
    @Upsert
    suspend fun insertCollection(collection: Collection)
    @Upsert
    suspend fun insertRecipe(recipe: Recipe)
    @Upsert
    suspend fun insertCollectionRecipeCrossRef(collectionRecipeCrossRef: CollectionRecipeCrossRef)

    @Query("SELECT * FROM collection")
    suspend fun getCollections(): List<Collection>
    @Query("SELECT * FROM recipe")
    suspend fun getRecipes(): List<Recipe>
}