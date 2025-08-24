package com.example.foodify.datalocal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodify.dao.RecipeDao

@Database(entities = [Recipe::class], version = 2)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}