package com.example.foodify.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedRecipes(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val tags: List<String>
)
