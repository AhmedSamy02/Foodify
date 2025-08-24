package com.example.foodify.datalocal

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RecipeWithDetails(
    @Embedded val recipe: Recipe,

    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeOwnerId"
    )
    val ingredients: List<Ingredient> = emptyList(),

    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeOwnerId"
    )
    val steps: List<StepEntity> = emptyList(),

    @Relation(
        parentColumn = "recipeId",
        entityColumn = "collectionId",
        associateBy = Junction(CollectionRecipeCrossRef::class)
    )
    val collections: List<CollectionEntity> = emptyList()
)