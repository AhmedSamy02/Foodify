package com.example.foodify.data.local

import androidx.room.Entity

@Entity(tableName = "collection_recipe_cross_ref", primaryKeys = ["collectionId", "recipeId"])
data class CollectionRecipeCrossRef(
    val collectionId: Int,
    val recipeId: Int
)
