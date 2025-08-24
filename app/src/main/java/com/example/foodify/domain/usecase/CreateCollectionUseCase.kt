package com.example.foodify.domain.usecase

import com.example.foodify.domain.repository.CollectionRepository
import jakarta.inject.Inject
import com.example.foodify.domain.model.Collection

class CreateCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    suspend operator fun invoke(
        title: String,
        description: String = ""
    ): Result<Long> {
        return try {
            if (title.isBlank()) {
                return Result.failure(IllegalArgumentException("Collection name cannot be empty"))
            }

            val collection = Collection(
                title = title.trim(),
                description = description.trim(),
                id = TODO(),
                recipeCount = TODO(),
                createdAt = TODO(),
                updatedAt = TODO(),
                recipes = TODO()
            )

            val collectionId = repository.createCollection(collection)
            Result.success(collectionId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}