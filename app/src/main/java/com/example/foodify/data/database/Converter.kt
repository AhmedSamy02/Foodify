package com.example.foodify.data.database

import androidx.room.TypeConverter
import com.example.foodify.data.local.Recipe
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
    fun fromRecipeList(list: List<Recipe>): String {
        return Gson().toJson(list)
    }
    @TypeConverter
    fun toRecipeList(value: String): List<Recipe> {
        return Gson().fromJson(value, Array<Recipe>::class.java).toList()
    }


}