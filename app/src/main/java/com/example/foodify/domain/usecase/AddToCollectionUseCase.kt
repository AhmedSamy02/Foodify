package com.example.foodify.domain.usecase

import com.example.foodify.domain.repository.CollectionRepository
import jakarta.inject.Inject

class AddToCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    suspend operator fun invoke(recipeId: Int, collectionId: Int): Result<Unit> {
        return try {
            repository.addRecipeToCollection(recipeId, collectionId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}