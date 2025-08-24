package com.example.foodify.presentation.screens.collection

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodify.domain.model.Collection
import com.example.foodify.domain.usecase.AddToCollectionUseCase
import com.example.foodify.domain.usecase.EditCollectionUseCase
import com.example.foodify.domain.usecase.GetCollectionByIdUseCase
import com.example.foodify.domain.usecase.RemoveFromCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CollectionDetailViewModel @Inject constructor(
    private val getCollectionByIdUseCase: GetCollectionByIdUseCase,
    private val editCollectionUseCase: EditCollectionUseCase,
    private val addToCollectionUseCase: AddToCollectionUseCase,
    private val removeFromCollectionUseCase: RemoveFromCollectionUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _collection = MutableStateFlow<Collection?>(null)
    val collection: StateFlow<Collection?> = _collection.asStateFlow()

    fun loadCollection(collectionId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            getCollectionByIdUseCase(collectionId)
                .onSuccess { collectionData ->
                    _collection.value = collectionData
                }
                .onFailure { exception ->
                }
            _isLoading.value = false
        }
    }

    fun updateCollection(collection: Collection) {
        viewModelScope.launch {
            editCollectionUseCase(collection)
                .onSuccess {
                    loadCollection(collection.id)
                }
                .onFailure { exception ->
                }
        }
    }

    fun addRecipeToCollection(recipeId: Int, collectionId: Int) {
        viewModelScope.launch {
            addToCollectionUseCase(recipeId, collectionId)
                .onSuccess {
                    loadCollection(collectionId)
                }
                .onFailure { exception ->
                }
        }
    }

    fun removeRecipeFromCollection(recipeId: Int, collectionId: Int) {
        viewModelScope.launch {
            removeFromCollectionUseCase(recipeId, collectionId)
                .onSuccess {
                    loadCollection(collectionId)
                }
                .onFailure { exception ->
                }
        }
    }
}