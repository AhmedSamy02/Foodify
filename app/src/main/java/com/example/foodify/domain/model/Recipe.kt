package com.example.foodify.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val ingredients: List<String>,
    val steps: List<String>,
    val tags: List<String>
)