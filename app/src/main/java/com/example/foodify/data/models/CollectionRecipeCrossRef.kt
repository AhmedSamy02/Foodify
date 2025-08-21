package com.example.foodify.data.models

import androidx.room.Entity

@Entity(primaryKeys = ["collectionId", "recipeId"])
data class CollectionRecipeCrossRef(
    val collectionId: Int,
    val recipeId: Int
)
