package com.example.foodify.presentation.screens.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodify.R
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun SearchScreen() {
    var query by remember { mutableStateOf("") }
    var filterVisible by remember { mutableStateOf(false) }

    var selectedDifficulty by remember { mutableStateOf<String?>(null) }
    var selectedDishTypes by remember { mutableStateOf(setOf<String>()) }
    var cookingTime by remember { mutableStateOf(30f) }

    Box(Modifier.fillMaxSize()) {
        Column {
            SearchTopBar(
                query = query,
                onQueryChange = { query = it },
                onFilterClick = { filterVisible = true }
            )

            RecipeList(query = query)
        }

        // Sidebar filter drawer
        FilterSidebar(
            visible = filterVisible,
            onClose = { filterVisible = false },
            selectedDifficulty = selectedDifficulty,
            onDifficultySelected = { selectedDifficulty = it },
            selectedDishTypes = selectedDishTypes,
            onDishTypeToggle = {
                selectedDishTypes = if (selectedDishTypes.contains(it)) {
                    selectedDishTypes - it
                } else {
                    selectedDishTypes + it
                }
            },
            cookingTime = cookingTime,
            onCookingTimeChange = { cookingTime = it }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit
) {
    TopAppBar(
        title = {}, // no app title
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    placeholder = { Text("Search recipes...") },
                    singleLine = true,
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onFilterClick) {
                    Icon(
                        painter = painterResource(R.drawable.filter),
                        contentDescription = "Filter"
                    )
                }
            }
        }
    )
}

// ---------------------------
// Recipe list with image cards
// ---------------------------
data class Recipe(val name: String, val imageRes: Int)

@Composable
fun RecipeList(query: String) {
    val recipes = listOf(
        Recipe("Pancakes", R.drawable.pancakes),
        Recipe("Spaghetti", R.drawable.spaghetti),
        Recipe("Salad", R.drawable.salad),
        Recipe("Chocolate Cake with Buttercream Frosting", R.drawable.chocolatecake),
        Recipe("Steak with Mashed Potatoes", R.drawable.steak)
    ).filter { it.name.contains(query, ignoreCase = true) }

    Column(Modifier.padding(16.dp)) {
        recipes.forEach { recipe ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Box(modifier = Modifier.height(180.dp)) {
                    Image(
                        painter = painterResource(id = recipe.imageRes),
                        contentDescription = recipe.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    // Gradient overlay for readability
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.6f)
                                    ),
                                    startY = 100f
                                )
                            )
                    )
                    // Recipe name at bottom
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}


// ---------------------------
// Filter Sidebar
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterSidebar(
    visible: Boolean,
    onClose: () -> Unit,
    selectedDifficulty: String?,
    onDifficultySelected: (String) -> Unit,
    selectedDishTypes: Set<String>,
    onDishTypeToggle: (String) -> Unit,
    cookingTime: Float,
    onCookingTimeChange: (Float) -> Unit,
    onClearAll: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            initialOffsetX = { it }, animationSpec = tween(300)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it }, animationSpec = tween(300)
        )
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
                .clickable { onClose() }
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .background(Color.White)
                    .align(Alignment.CenterEnd)
            ) {
                // 🔶 Yellow Header at top
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFEB3B), RoundedCornerShape(8.dp))
                        .padding(vertical = 16.dp, horizontal = 12.dp)
                ) {
                    Text(
                        "Filter",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                // Push options slightly lower
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.Top
                ) {

                    // Cooking Time
                    SectionLabel("Cooking Time")
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF1565C0), RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        Column {
                            Text("${cookingTime.toInt()} mins", color = Color.White)
                            Slider(
                                value = cookingTime,
                                onValueChange = onCookingTimeChange,
                                valueRange = 0f..120f,
                                colors = SliderDefaults.colors(
                                    thumbColor = Color.White,
                                    activeTrackColor = Color.White,
                                    inactiveTrackColor = Color.White.copy(alpha = 0.3f)
                                )
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Difficulty
                    SectionLabel("Difficulty")
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF0D47A1), RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        FlowRow(mainAxisSpacing = 8.dp, crossAxisSpacing = 8.dp) {
                            listOf("Easy", "Medium", "Hard").forEach { level ->
                                val selected = selectedDifficulty == level
                                Box(
                                    Modifier
                                        .background(
                                            if (selected) Color(0xFFFFEB3B) else Color(0xFF1976D2),
                                            RoundedCornerShape(16.dp)
                                        )
                                        .clickable { onDifficultySelected(level) }
                                        .padding(horizontal = 12.dp, vertical = 8.dp)
                                ) {
                                    Text(
                                        level,
                                        color = if (selected) Color.Black else Color.White
                                    )
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Dish Type
                    SectionLabel("Dish Type")
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF1565C0), RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        FlowRow(mainAxisSpacing = 8.dp, crossAxisSpacing = 8.dp) {
                            listOf(
                                "Breakfast",
                                "Lunch",
                                "Snack",
                                "Brunch",
                                "Dessert",
                                "Dinner",
                                "Appetizers"
                            )
                                .forEach { type ->
                                    val selected = selectedDishTypes.contains(type)
                                    Box(
                                        Modifier
                                            .background(
                                                if (selected) Color(0xFFFFEB3B) else Color(
                                                    0xFF1976D2
                                                ),
                                                RoundedCornerShape(16.dp)
                                            )
                                            .clickable { onDishTypeToggle(type) }
                                            .padding(horizontal = 12.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            type,
                                            color = if (selected) Color.Black else Color.White
                                        )
                                    }
                                }
                        }
                    }
                }

                // Buttons
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onClearAll,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text("Clear All", color = Color.White)
                    }
                    Button(
                        onClick = onConfirm,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text("Confirm", color = Color.White)
                    }
                }
            }
        }
    }
}


@Composable
private fun SectionLabel(text: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFFE65100), RoundedCornerShape(8.dp))
            .padding(vertical = 6.dp, horizontal = 12.dp)
    ) {
        Text(text, color = Color.White)
    }
}
