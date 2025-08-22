package com.example.foodify.di

import android.content.Context
import androidx.room.Room
import com.example.foodify.database.FoodifyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideFoodifyDatabase(@ApplicationContext context: Context): FoodifyDatabase {
        return Room.databaseBuilder(
                context,
                FoodifyDatabase::class.java,
                "foodify_database"
            ).fallbackToDestructiveMigration(false).build()
    }
    @Singleton
    @Provides
    fun provideFoodifyDao(foodifyDatabase: FoodifyDatabase) = foodifyDatabase.dao

}