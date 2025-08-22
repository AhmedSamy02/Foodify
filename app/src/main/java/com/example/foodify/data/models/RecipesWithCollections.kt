package com.example.foodify.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RecipeWithCollections(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CollectionRecipeCrossRef::class,
            parentColumn = "recipeId",
            entityColumn = "collectionId"
        )
    )
    val collections: List<Collection>
)
