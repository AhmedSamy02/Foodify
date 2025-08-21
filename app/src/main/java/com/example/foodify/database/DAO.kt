package com.example.foodify.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.foodify.data.models.Collection
import com.example.foodify.data.models.CollectionRecipeCrossRef
import com.example.foodify.data.models.CollectionWithRecipes
import com.example.foodify.data.models.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Upsert
    suspend fun insertCollection(collection: Collection)
    @Upsert
    suspend fun insertRecipe(recipe: Recipe)
    @Upsert
    suspend fun insertCollectionRecipeCrossRef(collectionRecipeCrossRef: CollectionRecipeCrossRef)

    @Query("SELECT * FROM collection")
    suspend fun getCollections(): Flow<List<Collection>>
    @Query("SELECT * FROM recipe")
    suspend fun getRecipes(): Flow<List<Recipe>>

    //Relations: collections with their recipes
    @Transaction
    @Query("SELECT * FROM collection")
    suspend fun getCollectionsWithRecipes(): Flow<List<CollectionWithRecipes>>
    @Transaction
    @Query("SELECT * FROM collection WHERE id = :collectionId")
    suspend fun getCollectionWithRecipes(collectionId: Int): Flow<CollectionWithRecipes>

    //Update / Delete
    @Query("UPDATE collection SET title = :newTitle WHERE id = :collectionId")
    suspend fun renameCollection(collectionId: Int, newTitle: String)

    @Query("DELETE FROM collection WHERE id = :collectionId")
    suspend fun deleteCollection(collectionId: Int)

    @Query("DELETE FROM recipe WHERE id = :recipeId")
    suspend fun deleteRecipe(recipeId: Int)

  @Query("DELETE FROM collectionrecipecrossref WHERE collectionId = :collectionId AND recipeId = :recipeId")
  suspend fun deleteCollectionRecipeCrossRef(collectionId: Int, recipeId: Int)


}