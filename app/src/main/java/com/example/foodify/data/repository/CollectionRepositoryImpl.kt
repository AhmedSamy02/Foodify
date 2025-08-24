package com.example.foodify.data.repository

import com.example.foodify.data.database.CollectionDao
import com.example.foodify.data.local.CollectionEntity
import com.example.foodify.data.local.CollectionRecipeCrossRef
import com.example.foodify.data.local.CollectionWithRecipes
import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.domain.model.Collection
import com.example.foodify.domain.model.Recipe
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

    override suspend fun renameCollection(collectionId: Int, newName: String) {
        val entity = collectionDao.getCollectionById(collectionId)?.copy(title = newName)
        entity?.let { collectionDao.updateCollection(it) }
    }

    override suspend fun deleteCollection(collectionId: Int) {
        val entity = collectionDao.getCollectionById(collectionId)
        entity?.let { collectionDao.deleteCollection(it) }
    }

    override suspend fun addRecipeToCollection(collectionId: Int, recipeId: Int) {
        collectionDao.insertCollectionRecipeCrossRef(
            CollectionRecipeCrossRef(collectionId, recipeId)
        )
    }

    override suspend fun removeRecipeFromCollection(
        collectionId: Int,
        recipeId: Int
    ) {
        collectionDao.deleteCollectionRecipeCrossRef(
            CollectionRecipeCrossRef(collectionId, recipeId)
        )
    }

    override fun getCollections(): Flow<List<Collection>> {
        return collectionDao.getAllCollectionsWithRecipes().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getRecipesInCollection(collectionId: Int): Flow<List<Recipe>> {
        return collectionDao.getCollectionWithRecipes(collectionId).map { relation ->
            relation?.recipes?.map { it.toDomain() } ?: emptyList()
        }
    }

    //mappers

    private fun CollectionWithRecipes.toDomain(): Collection {
        return Collection(
            id = collection.id,
            title = collection.title,
            recipes = recipes.map { it.toDomain() }
        )
    }



    private fun Collection.toEntity(): CollectionEntity {
        return CollectionEntity(
            id = id,
            title = title
        )
    }

    private fun RecipeEntity.toDomain(): Recipe {
        return Recipe(
            id = id,
            title = title,
            imageUrl = imageUrl,
            ingredients = ingredients,
            steps = steps,
            tags = tags
        )
    }

    private fun Recipe.toEntity(): RecipeEntity {
        return RecipeEntity(
            id = id,
            title = title,
            imageUrl = imageUrl,
            ingredients = ingredients,
            steps = steps,
            tags = tags
        )
    }



}