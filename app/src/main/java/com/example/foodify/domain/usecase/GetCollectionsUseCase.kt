package com.example.foodify.domain.usecase

import com.example.foodify.domain.model.Collection
import com.example.foodify.domain.model.Recipe

import com.example.foodify.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCollectionsUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    operator fun invoke(): Flow<List<Collection>> {
        return repository.getAllCollectionsWithRecipes()
    }
}