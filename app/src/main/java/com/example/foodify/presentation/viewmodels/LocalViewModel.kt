package com.example.foodify.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.foodify.data.local.CollectionRecipeCrossRef
import com.example.foodify.data.local.Recipe
import com.example.foodify.data.local.Collection
import com.example.foodify.data.repository.RecipeRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(
    private val localRepository: RecipeRepositoryImp
): ViewModel(){
    suspend fun insertRecipe(recipe: Recipe){
        localRepository.addRecipe(recipe)
    }
//    suspend fun getRecipes(): List<Recipe>{
//        return localRepository.getRecipes()
//    }

//    suspend fun insertCollection(collection: Collection){
//        localRepository.insertCollection(collection)
//    }
//    suspend fun getCollections(): List<Collection>{
//        return localRepository.getCollections()
//    }

//    suspend fun insertCollectionRecipeCrossRef(collectionRecipeCrossRef: CollectionRecipeCrossRef){
//        localRepository.insertCollectionRecipeCrossRef(collectionRecipeCrossRef)
//    }

}