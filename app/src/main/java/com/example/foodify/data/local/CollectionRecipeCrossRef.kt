package com.example.foodify.data.local

import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "collection_recipe_cross_ref", primaryKeys = ["collectionId", "recipeId"],
    indices = [
        Index(value = ["collectionId"]),
        Index(value = ["recipeId"])
    ]
)
data class CollectionRecipeCrossRef(
    val collectionId: Int,
    val recipeId: Int
)
