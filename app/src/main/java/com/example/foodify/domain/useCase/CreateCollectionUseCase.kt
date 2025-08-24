package com.example.foodify.domain.useCase

import com.example.foodify.data.local.CollectionEntity
import com.example.foodify.domain.repository.CollectionRepository
import javax.inject.Inject

class CreateCollectionUseCase @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend operator fun invoke(collection: CollectionEntity) {
        collectionRepository.createCollection(collection)


    }
}