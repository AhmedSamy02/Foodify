package com.example.foodify.di

import android.content.Context
import androidx.room.Room
import com.example.foodify.data.database.CollectionDao
import com.example.foodify.data.database.FoodifyDatabase
import com.example.foodify.data.database.RecipeDao
import com.example.foodify.data.repository.CollectionRepositoryImpl
import com.example.foodify.data.repository.RecipeRepositoryImp
import com.example.foodify.domain.repository.CollectionRepository
import com.example.foodify.domain.repository.RecipeRepository
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
        ).build()
    }
    @Singleton
    @Provides
    fun provideRecipeDao(foodifyDatabase: FoodifyDatabase): RecipeDao {
        return foodifyDatabase.recipeDao()
    }

    @Singleton
    @Provides
    fun provideCollectionDao(foodifyDatabase: FoodifyDatabase) : CollectionDao {
        return foodifyDatabase.collectionDao()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(db: FoodifyDatabase): RecipeRepository {
        return RecipeRepositoryImp(db.recipeDao())
    }

    @Provides
    @Singleton
    fun provideCollectionRepository(db: FoodifyDatabase): CollectionRepository {
        return CollectionRepositoryImpl(db.collectionDao())
    }


}