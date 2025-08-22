package com.example.foodify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.foodify.data.local.Recipe
import com.example.foodify.data.local.Collection
import com.example.foodify.presentation.navigation.NavigationStack
import com.example.foodify.presentation.theme.FoodifyTheme
import com.example.foodify.presentation.viewmodels.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<LocalViewModel>()
//    val recipe = Recipe(
//        id = 1,
//        title = "Pizza",
//        ingredients = listOf("Pizza Ingredients", "Pizza Ingredients"),
//        steps = listOf("Pizza Instructions", "Pizza Instructions"),
//        tags = listOf("Pizza", "Italian"),
//    )
//    val collection = Collection(
//        id = 1,
//        title = "Pizza",
//        recipes = listOf(recipe,recipe)
//    )
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodifyTheme {
                NavigationStack()
            }
        }
        lifecycleScope.launch {
//            testRecipeInsertionAndRetrieval(viewModel,recipe)
//            testCollectionInsertionAndRetrieval(viewModel,collection)
        }
    }
    private suspend fun testRecipeInsertionAndRetrieval(viewModel: LocalViewModel,recipe: Recipe){
            viewModel.insertRecipe(recipe)
//            val recipes = viewModel.getRecipes()
//            println(recipes)
    }
//    private suspend fun testCollectionInsertionAndRetrieval(viewModel: LocalViewModel,collection: Collection){
//        viewModel.insertCollection(collection)
//        val collections = viewModel.getCollections()
//        println(collections)
//    }
}

