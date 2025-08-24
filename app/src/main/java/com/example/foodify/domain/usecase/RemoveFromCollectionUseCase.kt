package com.example.foodify.domain.usecase




import com.example.foodify.domain.repository.CollectionRepository
import javax.inject.Inject

class RemoveFromCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    suspend operator fun invoke(recipeId: Int, collectionId: Int): Result<Unit> {
        return try {
            repository.removeRecipeFromCollection(recipeId, collectionId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}