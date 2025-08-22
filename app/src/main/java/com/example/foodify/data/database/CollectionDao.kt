package com.example.foodify.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodify.data.local.Collection
import com.example.foodify.data.local.CollectionRecipeCrossRef
import com.example.foodify.data.local.Recipe
import kotlinx.coroutines.flow.Flow

interface CollectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(collection: Collection)

    @Update
    suspend fun updateCollection(collection: Collection)

    @Delete
    suspend fun deleteCollection(collection: Collection)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollectionRecipeCrossRef(crossRef: CollectionRecipeCrossRef)

    @Delete
    suspend fun deleteCollectionRecipeCrossRef(crossRef: CollectionRecipeCrossRef)

    @Query("SELECT * FROM collections")
    fun getAllCollections(): Flow<List<Collection>>

    @Query("SELECT R.* FROM recipes R INNER JOIN collection_recipe_cross_ref CR ON R.id = CR.recipeId WHERE CR.collectionId = :collectionId")
    fun getRecipesForCollection(collectionId: String): Flow<List<Recipe>>

    @Query("SELECT * FROM collections WHERE id = :collectionId")
    suspend fun getCollectionById(collectionId: String): Collection?
}


