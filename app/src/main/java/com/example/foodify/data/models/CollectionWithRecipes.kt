package com.example.foodify.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CollectionWithRecipes(
    @Embedded val collection: Collection,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CollectionRecipeCrossRef::class,
            parentColumn = "collectionId",
            entityColumn = "recipeId"
        )
    )
    val recipes: List<Recipe>
)
