package com.example.foodify.domain.model

data class Collection(
    val id: Int,
    val title: String,
    val description: String = "",
    val recipeCount: Int = recipes.size,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val recipes: List<Recipe> = emptyList()
)