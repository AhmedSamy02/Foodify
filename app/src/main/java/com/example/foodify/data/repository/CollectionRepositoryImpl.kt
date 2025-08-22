package com.example.foodify.data.repository

import com.example.foodify.data.database.CollectionDao
import com.example.foodify.data.local.Collection
import com.example.foodify.data.local.CollectionRecipeCrossRef
import com.example.foodify.data.local.Recipe
import com.example.foodify.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val collectionDao: CollectionDao
): CollectionRepository {
    override suspend fun createCollection(collection: Collection) {
        collectionDao.insertCollection(collection.toEntity())
    }

    override suspend fun renameCollection(collectionId: String, newName: String) {
       val collection = collectionDao.getCollectionById(collectionId)?.copy(title = newName)
        collection?.let { collectionDao.updateCollection(it) }
    }

    override suspend fun deleteCollection(collectionId: String) {
        val collection = collectionDao.getCollectionById(collectionId)
        collection?.let { collectionDao.deleteCollection(it) }
    }

    override suspend fun addRecipeToCollection(collectionId: Int, recipeId: Int) {
        collectionDao.insertCollectionRecipeCrossRef(
            CollectionRecipeCrossRef(collectionId, recipeId)
        )
    }

    override suspend fun removeRecipeFromCollection(collectionId: Int, recipeId: Int) {
        collectionDao.deleteCollectionRecipeCrossRef(
            CollectionRecipeCrossRef(collectionId, recipeId)
        )
    }

    override fun getCollections(): Flow<List<Collection>> {
        return collectionDao.getAllCollections() .map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getRecipesInCollection(collectionId: String): Flow<List<Recipe>> {
        return collectionDao.getRecipesForCollection(collectionId).map { entities ->
            entities.map { it.toDomain() }
        }

    }
    private fun Collection.toEntity(): Collection {
        return Collection(
            id = id,
            title = title,
            recipes = recipes
        )
    }
    private fun Collection.toDomain(): Collection {
        return Collection(
            id = id,
            title = title,
            recipes = recipes
        )
    }

    private fun Recipe.toDomain(): Recipe {
        return Recipe(
            id = id,
            title = title,
            imageUrl = imageUrl,
            ingredients = ingredients,
            steps = steps,
            tags = tags
        )
    }



}