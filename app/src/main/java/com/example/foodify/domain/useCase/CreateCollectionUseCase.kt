package com.example.foodify.domain.useCase

import com.example.foodify.data.local.Collection
import com.example.foodify.domain.repository.CollectionRepository
import javax.inject.Inject

class CreateCollectionUseCase @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend operator fun invoke(collection: Collection) {
        collectionRepository.createCollection(collection)


    }
}