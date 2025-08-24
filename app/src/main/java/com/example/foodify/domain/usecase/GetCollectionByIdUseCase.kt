package com.example.foodify.domain.usecase


import com.example.foodify.domain.model.Collection
import com.example.foodify.domain.model.Recipe

import com.example.foodify.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCollectionByIdUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    suspend operator fun invoke(collectionId: Int): Result<Collection?> {
        return try {
            val collection = repository.getCollectionById(collectionId)
            Result.success(collection)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}