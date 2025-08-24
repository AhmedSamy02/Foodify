package com.example.foodify.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodify.data.local.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getRecipeById(id: String): RecipeEntity?

    @Query("""
        SELECT * FROM recipes
        WHERE (:title IS NULL OR title LIKE '%' || :title || '%')
        AND (:difficulty IS NULL OR difficulty = :difficulty)
        AND (:dishTypesCount = 0 OR dishType IN (:dishTypes))
        AND (:maxCookingTime IS NULL OR cookTime <= :maxCookingTime)
    """)
    fun searchRecipes(
        title: String?,
        difficulty: String?,
        dishTypes: List<String>,
        dishTypesCount: Int = dishTypes.size,
        maxCookingTime: Int?
    ): Flow<List<RecipeEntity>>

//    @Query("SELECT * FROM recipes WHERE tags LIKE '%' || :tag || '%'")
//    fun getRecipesByTag(tag: String): Flow<List<RecipeEntity>>

}