package com.example.foodify.data.repository

import com.example.foodify.data.models.Collection
import com.example.foodify.data.models.CollectionRecipeCrossRef
import com.example.foodify.data.models.Recipe
import com.example.foodify.database.DAO
import com.example.foodify.domain.repository.LocalRepository
import jakarta.inject.Inject

class LocalRepositoryImp @Inject constructor(
    private val dao: DAO
) : LocalRepository  {
    override suspend fun insertCollection(collection: Collection) = dao.insertCollection(collection)
    override suspend fun insertRecipe(recipe: Recipe) = dao.insertRecipe(recipe)
    override suspend fun insertCollectionRecipeCrossRef(collectionRecipeCrossRef: CollectionRecipeCrossRef) = dao.insertCollectionRecipeCrossRef(collectionRecipeCrossRef)
    override suspend fun getCollections(): List<Collection> = dao.getCollections()
    override suspend fun getRecipes(): List<Recipe> = dao.getRecipes()
}