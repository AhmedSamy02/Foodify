package com.example.foodify.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodify.domain.model.Recipe
import com.example.foodify.domain.repository.CollectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.foodify.domain.model.Collection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val repository: CollectionRepository
) : ViewModel() {

    // All collections with recipes
    val collections: StateFlow<List<Collection>> = repository
        .getCollections()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Selected collection’s recipes
    private val _selectedCollectionId = MutableStateFlow<Int?>(null)
    @OptIn(ExperimentalCoroutinesApi::class)
    val recipesInCollection: StateFlow<List<Recipe>> =
        _selectedCollectionId
            .filterNotNull()
            .flatMapLatest { repository.getRecipesInCollection(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )



    fun createCollection(title: String) {
        viewModelScope.launch {
            val newCollection = Collection(
                id = 0, // autoGenerate by Room
                title = title
            )
            repository.createCollection(newCollection)
        }
    }

    fun renameCollection(collectionId: Int, newName: String) {
        viewModelScope.launch {
            repository.renameCollection(collectionId, newName)
        }
    }

    fun deleteCollection(collectionId: Int) {
        viewModelScope.launch {
            repository.deleteCollection(collectionId)
        }
    }

    fun addRecipeToCollection(collectionId: Int, recipeId: Int) {
        viewModelScope.launch {
            repository.addRecipeToCollection(collectionId, recipeId)
        }
    }

    fun removeRecipeFromCollection(collectionId: Int, recipeId: Int) {
        viewModelScope.launch {
            repository.removeRecipeFromCollection(collectionId, recipeId)
        }
    }

    fun selectCollection(collectionId: Int) {
        _selectedCollectionId.value = collectionId
    }
}
