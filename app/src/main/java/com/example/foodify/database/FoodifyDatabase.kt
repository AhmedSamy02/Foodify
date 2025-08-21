package com.example.foodify.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodify.data.models.CollectionRecipeCrossRef
import com.example.foodify.data.models.Recipe
import com.example.foodify.data.models.Collection

@Database(entities = [Collection::class, Recipe::class, CollectionRecipeCrossRef::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class FoodifyDatabase : RoomDatabase() {
    abstract val dao: DAO
}