package com.example.foodify.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodify.data.local.CollectionRecipeCrossRef
import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.data.local.CollectionEntity

@Database(entities = [CollectionEntity::class, RecipeEntity::class, CollectionRecipeCrossRef::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class FoodifyDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun collectionDao(): CollectionDao
}