package com.example.foodify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.datalocal.Recipe
import com.example.foodify.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    var query by mutableStateOf("")
    var filterVisible by mutableStateOf(false)
    var cookingTime by mutableFloatStateOf(48f)
    var selectedDifficulty by mutableStateOf<String?>(null)
    var selectedDishTypes = mutableStateListOf<String>()
    private val _recipes = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val recipes: StateFlow<List<RecipeEntity>> = _recipes.asStateFlow()

    init {
        _recipes.value = listOf(
            RecipeEntity(1, "Pancakes", "Easy", listOf(),listOf(),"Easy", "Breakfast",45),
            RecipeEntity(2, "Spaghetti Bolognese", "Medium", listOf(),listOf(),"Easy", "Breakfast",45),
            RecipeEntity(3, "Chocolate Bolognese", "Medium", listOf(),listOf(),"Easy", "Breakfast",45),
            RecipeEntity(4, "Spaghetti ", "Medium", listOf(),listOf(),"Easy", "Breakfast",45),
        )

        viewModelScope.launch {
            combine(
                snapshotFlow { query },
                snapshotFlow { cookingTime },
                snapshotFlow { selectedDifficulty },
                snapshotFlow { selectedDishTypes.toList() }
            ) { q, time, diff, types -> Triple(q, diff, types) to time }
                .flatMapLatest { (triple, time) ->
                    val (q, diff, types) = triple
                    repository.searchRecipes(
                        title = if (q.isBlank()) null else q,
                        difficulty = diff,
                        dishTypes = types,
                        maxCookingTime = time.toInt()
                    )
                }.collect { result ->
                    _recipes.value = result
                }
        }
    }

    fun toggleDishType(type: String) {
        if (selectedDishTypes.contains(type)) selectedDishTypes.remove(type)
        else selectedDishTypes.add(type)
    }
}