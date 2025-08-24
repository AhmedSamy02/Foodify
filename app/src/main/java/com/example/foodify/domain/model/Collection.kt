package com.example.foodify.domain.model

data class Collection(
    val id: Int,
    val title: String,
    val recipes: List<Recipe> = emptyList()
)