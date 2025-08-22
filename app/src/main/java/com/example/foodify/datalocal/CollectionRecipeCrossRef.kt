package com.example.foodify.datalocal
import androidx.room.Entity

@Entity(primaryKeys = ["collectionId", "recipeId"])
data class CollectionRecipeCrossRef(
    val collectionId: Int,
    val recipeId: Int
)
