package com.example.foodify.presentation.screens.collection
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodify.presentation.navigation.Screen
import com.example.foodify.presentation.viewmodels.CollectionViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionDetailScreen(
    navController: NavController,
    collectionId: Int,
    viewModel: CollectionViewModel = hiltViewModel()
) {
    LaunchedEffect(collectionId) {
        viewModel.selectCollection(collectionId)
    }

    val recipes by viewModel.recipesInCollection.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Collection") },
                actions = {
                    IconButton(onClick = { viewModel.deleteCollection(collectionId); navController.popBackStack() }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                    IconButton(onClick = { /* rename logic */ }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = padding
        ) {
            items(recipes) { recipe ->
                RecipeCard(recipe) {
                    navController.navigate(Screen.RecipeDetail.createRoute(recipe.id))
                }
            }
        }
    }
}
