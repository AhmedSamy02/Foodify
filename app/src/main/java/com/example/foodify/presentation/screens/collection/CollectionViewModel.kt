package com.example.foodify.presentation.screens.collection

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodify.domain.model.Collection
import com.example.foodify.domain.model.Recipe
import com.example.foodify.domain.repository.CollectionRepository
import com.example.foodify.domain.usecase.AddToCollectionUseCase
import com.example.foodify.domain.usecase.CreateCollectionUseCase
import com.example.foodify.domain.usecase.DeleteCollectionUseCase
import com.example.foodify.domain.usecase.EditCollectionUseCase
import com.example.foodify.domain.usecase.GetCollectionByIdUseCase
import com.example.foodify.domain.usecase.GetCollectionsUseCase
import com.example.foodify.domain.usecase.RemoveFromCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CollectionUiState(
    val collections: List<Collection> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getCollectionsUseCase: GetCollectionsUseCase,
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val editCollectionUseCase: EditCollectionUseCase,
    private val deleteCollectionUseCase: DeleteCollectionUseCase,
    private val addToCollectionUseCase: AddToCollectionUseCase,
    private val removeFromCollectionUseCase: RemoveFromCollectionUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _collections = MutableStateFlow<List<Collection>>(emptyList())
    val collections: StateFlow<List<Collection>> = _collections.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val uiState: StateFlow<CollectionUiState> = combine(
        _collections,
        _isLoading
    ) { collections, isLoading ->
        CollectionUiState(
            collections = collections,
            isLoading = isLoading
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CollectionUiState()
    )

    val filteredCollections: StateFlow<List<Collection>> = combine(
        collections,
        searchQuery
    ) { collections, query ->
        if (query.isBlank()) {
            collections
        } else {
            collections.filter { collection ->
                collection.title.contains(query, ignoreCase = true) ||
                        collection.description.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        loadCollections()
    }

    fun loadCollections() {
        viewModelScope.launch {
            _isLoading.value = true
            getCollectionsUseCase()
                .collect { collections ->
                    _collections.value = collections
                    _isLoading.value = false
                }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun createCollection(name: String, description: String = "") {
        viewModelScope.launch {
            createCollectionUseCase(name, description)
                .onSuccess { collectionId ->
                }
                .onFailure { exception ->
                }
        }
    }

    fun updateCollection(collection: Collection) {
        viewModelScope.launch {
            editCollectionUseCase(collection)
                .onSuccess {
                }
                .onFailure { exception ->
                }
        }
    }

    fun deleteCollection(collection: Collection) {
        viewModelScope.launch {
            deleteCollectionUseCase(collection)
                .onSuccess {
                }
                .onFailure { exception ->
                }
        }
    }

    fun addRecipeToCollection(recipeId: Int, collectionId: Int) {
        viewModelScope.launch {
            addToCollectionUseCase(recipeId, collectionId)
                .onSuccess {
                }
                .onFailure { exception ->
                }
        }
    }

    fun removeRecipeFromCollection(recipeId: Int, collectionId: Int) {
        viewModelScope.launch {
            removeFromCollectionUseCase(recipeId, collectionId)
                .onSuccess {
                }
                .onFailure { exception ->
                }
        }
    }
}