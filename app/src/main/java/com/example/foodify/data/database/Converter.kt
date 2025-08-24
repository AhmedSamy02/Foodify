package com.example.foodify.data.database

import androidx.room.TypeConverter
import com.example.foodify.data.local.RecipeEntity
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }
    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }
    @TypeConverter
    fun fromRecipeList(list: List<RecipeEntity>): String {
        return Gson().toJson(list)
    }
    @TypeConverter
    fun toRecipeList(value: String): List<RecipeEntity> {
        return Gson().fromJson(value, Array<RecipeEntity>::class.java).toList()
    }


}