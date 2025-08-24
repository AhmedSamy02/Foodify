package com.example.foodify.data.repository

import android.R.attr.id
import android.R.attr.name
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
    override suspend fun createCollection(collection: Collection): Long {
        return collectionDao.insertCollection(collection.toEntity())
    }



    override suspend fun renameCollection(collection: Collection) {
      collectionDao.updateCollection(collection.toEntity())
    }

    override suspend fun deleteCollection(collection: Collection) {
        collectionDao.deleteCollection(collection.toEntity())
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

//    override fun getCollections(): Flow<List<Collection>> {
//        return collectionDao.getAllCollectionsWithRecipes().map { list ->
//            list.map { it.toDomain() }
//        }
//    }

    override suspend fun getCollectionById(id: Int): Collection? {
        return collectionDao.getCollectionById(id)?.toDomain()
    }

    override fun getAllCollectionsWithRecipes(): Flow<List<Collection>> {
        return collectionDao.getAllCollectionsWithRecipes().map { collectionsWithRecipes ->
            collectionsWithRecipes.map { collectionWithRecipes ->
                Collection(
                    id = collectionWithRecipes.collection.id,
                    title = collectionWithRecipes.collection.title,
                    description = collectionWithRecipes.collection.description,
                    recipes = collectionWithRecipes.recipes.map { it.toDomain() },
                    recipeCount = collectionWithRecipes.recipes.size,
                    createdAt = collectionWithRecipes.collection.createdAt,
                    updatedAt = collectionWithRecipes.collection.updatedAt
                )
            }
        }
    }


    //mappers
    fun CollectionEntity.toDomain(): Collection {
        return Collection(
            id = id,
            title = title,
            description = description,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    fun Collection.toEntity(): CollectionEntity {
        return CollectionEntity(
            id = id,
            title = title,
            description = description,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun CollectionWithRecipes.toDomain(): Collection {
        return Collection(
            id = collection.id,
            title = collection.title,
            recipes = recipes.map { it.toDomain() }
        )
    }





    private fun RecipeEntity.toDomain(): Recipe {
        return Recipe(
            id = id,
            title = title,
            imageUrl = imageUrl,
            ingredients = ingredients,
            steps = steps,
            tags = listOf()
        )
    }





}