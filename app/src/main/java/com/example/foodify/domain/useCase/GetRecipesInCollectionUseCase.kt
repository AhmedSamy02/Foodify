package com.example.foodify.domain.useCase

import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.domain.repository.CollectionRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetRecipesInCollectionUseCase  @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    operator fun invoke(collectionId: String): Flow<List<RecipeEntity>> {
        return collectionRepository.getRecipesInCollection(collectionId)
    }
}