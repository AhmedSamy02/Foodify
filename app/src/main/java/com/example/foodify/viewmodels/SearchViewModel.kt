package com.example.foodify.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodify.datalocal.Recipe
import com.example.foodify.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    var query by mutableStateOf("")
    var filterVisible by mutableStateOf(false)
    var cookingTime by mutableStateOf(48f)
    var selectedDifficulty by mutableStateOf<String?>(null)
    var selectedDishTypes = mutableStateListOf<String>()
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()
    val filteredRecipes: List<Recipe>
        get() = _recipes.value.filter { recipe ->
            val matchesQuery = recipe.title.contains(query, ignoreCase = true)
            val matchesDifficulty = selectedDifficulty?.let { it == recipe.difficulty } ?: true
            val matchesDishType = if (selectedDishTypes.isNotEmpty()) selectedDishTypes.contains(recipe.dishType) else true
            val matchesCookingTime = recipe.cookTime <= cookingTime.toInt()
            matchesQuery && matchesDifficulty && matchesDishType && matchesCookingTime
        }

    init {
        _recipes.value = listOf(
            Recipe(1, "Pancakes", "Easy", "Breakfast", 20),
            Recipe(2, "Spaghetti Bolognese", "Medium", "Lunch", 45),
            Recipe(3, "Chocolate Cake", "Hard", "Dessert", 90),
            Recipe(4, "Salad", "Easy", "Snack", 10)
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
