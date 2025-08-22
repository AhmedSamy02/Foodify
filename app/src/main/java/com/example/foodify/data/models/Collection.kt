package com.example.foodify.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Collection(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val recipes: List<Recipe>
)
