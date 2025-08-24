package com.example.foodify.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val imageUrl: String?,
    val ingredients: List<String>,
    val steps: List<String>,
    val tags: List<String>
    )