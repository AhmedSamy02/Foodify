package com.example.foodify.di

import android.content.Context
import androidx.room.Room
<<<<<<< HEAD
import com.example.foodify.dao.RecipeDao
import com.example.foodify.data.repository.RecipeRepository
import com.example.foodify.data.repository.RecipeRepositoryImpl
import com.example.foodify.datalocal.RecipeDatabase
=======
import com.example.foodify.database.FoodifyDatabase
>>>>>>> ae251d8a16acf6f892e0157263f840bda7825f85
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
<<<<<<< HEAD
    @Provides
    @Singleton
    fun provideRepository(dao: RecipeDao): RecipeRepository = RecipeRepositoryImpl(dao)
=======
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

>>>>>>> ae251d8a16acf6f892e0157263f840bda7825f85
}