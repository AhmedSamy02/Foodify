package com.example.foodify.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.foodify.datalocal.Recipe
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {

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
    ): Flow<List<Recipe>>
}
