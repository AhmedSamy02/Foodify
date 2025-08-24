// DatabaseModule.kt
package com.example.foodify.di

import android.content.Context
import androidx.room.Room
import com.example.foodify.dao.RecipeDao
import com.example.foodify.datalocal.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RecipeDatabase {
        return Room.databaseBuilder(
            context,
            RecipeDatabase::class.java,
            "recipe_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRecipeDao(db: RecipeDatabase): RecipeDao = db.recipeDao()
}

