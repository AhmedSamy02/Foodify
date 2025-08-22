package com.example.foodify.viewmodels

import androidx.lifecycle.ViewModel
import com.example.foodify.data.models.CollectionRecipeCrossRef
import com.example.foodify.data.models.Recipe
import com.example.foodify.data.models.Collection
import com.example.foodify.data.repository.LocalRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(
    private val localRepository: LocalRepositoryImp
): ViewModel(){
    suspend fun insertRecipe(recipe: Recipe){
        localRepository.insertRecipe(recipe)
    }
    suspend fun getRecipes(): List<Recipe>{
        return localRepository.getRecipes()
    }

    suspend fun insertCollection(collection: Collection){
        localRepository.insertCollection(collection)
    }
    suspend fun getCollections(): List<Collection>{
        return localRepository.getCollections()
    }

    suspend fun insertCollectionRecipeCrossRef(collectionRecipeCrossRef: CollectionRecipeCrossRef){
        localRepository.insertCollectionRecipeCrossRef(collectionRecipeCrossRef)
    }

}