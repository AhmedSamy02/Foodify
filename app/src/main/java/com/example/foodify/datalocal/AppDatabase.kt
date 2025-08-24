package com.example.foodify.datalocal
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodify.dao.RecipeDao

@Database(
    entities = [
        Recipe::class,
        Ingredient::class,
        StepEntity::class,
        CollectionEntity::class,
        CollectionRecipeCrossRef::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}