package com.example.foodify.domain.usecase

import com.example.foodify.domain.repository.CollectionRepository
import jakarta.inject.Inject
import com.example.foodify.domain.model.Collection

class EditCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    suspend operator fun invoke(collection: Collection): Result<Unit> {
        return try {
            if (collection.title.isBlank()) {
                return Result.failure(IllegalArgumentException("Collection name cannot be empty"))
            }

            repository.renameCollection(collection)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}