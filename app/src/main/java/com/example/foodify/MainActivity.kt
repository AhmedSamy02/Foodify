package com.example.foodify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.foodify.data.local.RecipeEntity
import com.example.foodify.domain.model.Recipe
import com.example.foodify.presentation.navigation.NavigationStack
import com.example.foodify.presentation.screens.recipedetail.RecipeDetailScreen
import com.example.foodify.presentation.theme.FoodifyTheme
import com.example.foodify.presentation.viewmodels.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<LocalViewModel>()
    val sampleRecipe = Recipe(
        id = 0,
        title = "Perfect Homemade Pancake",
        imageUrl = null,
        ingredients = listOf(
            "2 cups all-purpose flour",
            "2 teaspoons baking powder",
            "2 tablespoons sugar",
            "1/2 teaspoon salt",
            "1.5 cups milk",
            "1 large egg",
            "2 tablespoons vegetable oil or melted butter",
            "Vanilla extract (optional)"
        ),
        steps = listOf(
            "In a large bowl, mix together flour, baking powder, sugar, and salt",
            "In a separate bowl, whisk together milk, egg, and oil or melted butter",
            "Add vanilla extract if desired",
            "Gradually add the liquid mixture to the dry ingredients until a smooth lump-free batter forms",
            "Heat a griddle or skillet over medium heat",
            "Pour about 1 tablespoon of batter onto the griddle for each pancake",
            "Cook until bubbles form on the surface, then flip",
            "Cook the other side until golden brown",
            "Serve warm with your favorite toppings"
        ),
        tags = listOf("Breakfast", "Easy", "Vegetarian")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodifyTheme {
//                NavigationStack()
                RecipeDetailScreen(NavController(this),sampleRecipe)
            }
        }
    }
    private suspend fun testRecipeInsertionAndRetrieval(viewModel: LocalViewModel,recipe: RecipeEntity){
            viewModel.insertRecipe(recipe)
//            val recipes = viewModel.getRecipes()
//

    }
//    private suspend fun testCollectionInsertionAndRetrieval(viewModel: LocalViewModel,collection: Collection){
//        viewModel.insertCollection(collection)
//        val collections = viewModel.getCollections()
//        println(collections)
//    }
}

