package com.example.foodify.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.foodify.data.local.CollectionEntity
import com.example.foodify.data.local.CollectionRecipeCrossRef
import com.example.foodify.data.local.CollectionWithRecipes
import com.example.foodify.data.local.RecipeEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface CollectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(collection: CollectionEntity): Long




    @Update
    suspend fun updateCollection(collection: CollectionEntity)

    @Delete
    suspend fun deleteCollection(collection: CollectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollectionRecipeCrossRef(crossRef: CollectionRecipeCrossRef)

    @Delete
    suspend fun deleteCollectionRecipeCrossRef(crossRef: CollectionRecipeCrossRef)

    @Transaction
    @Query("SELECT * FROM collections")
    fun getAllCollectionsWithRecipes(): Flow<List<CollectionWithRecipes>>

    @Transaction
    @Query("SELECT * FROM collections WHERE id = :collectionId")
    fun getCollectionWithRecipes(collectionId: Int): Flow<CollectionWithRecipes?>

    @Query("SELECT * FROM collections WHERE id = :collectionId")
    suspend fun getCollectionById(collectionId: Int): CollectionEntity?
}

