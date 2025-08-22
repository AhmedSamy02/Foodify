package com.example.foodify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
<<<<<<< HEAD
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
=======
import androidx.activity.viewModels
>>>>>>> ae251d8a16acf6f892e0157263f840bda7825f85
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.foodify.data.models.Recipe
import com.example.foodify.data.models.Collection
import com.example.foodify.ui.navigation.NavigationStack
import com.example.foodify.ui.search.SearchScreen
import com.example.foodify.ui.theme.FoodifyTheme
import com.example.foodify.viewmodels.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
/*
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<LocalViewModel>()
    val recipe = Recipe(
        id = 1,
        title = "Pizza",
        ingredients = listOf("Pizza Ingredients", "Pizza Ingredients"),
        steps = listOf("Pizza Instructions", "Pizza Instructions"),
        tags = listOf("Pizza", "Italian"),
    )
    val collection = Collection(
        id = 1,
        title = "Pizza",
        recipes = listOf(recipe,recipe)
    )
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
            testRecipeInsertionAndRetrieval(viewModel,recipe)
            testCollectionInsertionAndRetrieval(viewModel,collection)
        }
    }
    private suspend fun testRecipeInsertionAndRetrieval(viewModel: LocalViewModel,recipe: Recipe){
            viewModel.insertRecipe(recipe)
            val recipes = viewModel.getRecipes()
            println(recipes)
    }
    private suspend fun testCollectionInsertionAndRetrieval(viewModel: LocalViewModel,collection: Collection){
        viewModel.insertCollection(collection)
        val collections = viewModel.getCollections()
        println(collections)
    }
}
*/
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodifyTheme {


                SearchScreen()
            }
        }
    }
}



