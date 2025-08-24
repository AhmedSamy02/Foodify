package com.example.foodify.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodify.presentation.viewmodels.CollectionViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedCollectionsScreen(
    viewModel: CollectionViewModel,
    onCollectionClick: (Int) -> Unit,
    onAddCollection: () -> Unit
) {
    val collections by viewModel.collections.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Saved") }, actions = {
                IconButton(onClick = onAddCollection) {
                    Icon(Icons.Default.Add, contentDescription = "New Collection")
                }
            })
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(collections) { collection ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onCollectionClick(collection.id) },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(collection.title, style = MaterialTheme.typography.bodyLarge)
                        Text("${collection.recipes.size} recipes", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
