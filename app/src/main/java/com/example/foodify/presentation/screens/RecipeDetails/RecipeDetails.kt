package com.example.foodify.presentation.screens.recipedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.foodify.R
import com.example.foodify.domain.model.Recipe
import com.example.foodify.presentation.theme.MainColor
import com.example.foodify.presentation.theme.OnBoarding2Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(navController: NavController, recipe: Recipe) {
    var isBookmarked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipe Details", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { isBookmarked = !isBookmarked }) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Done else Icons.Default.Add,
                            contentDescription = "Bookmark",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor,
                )
            )
        },
        containerColor = Color(0xFFF5F5F5)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Recipe Image
            Box(modifier = Modifier.fillMaxWidth()) {
                if (recipe.imageUrl != null) {
                    AsyncImage(
                        model = recipe.imageUrl,
                        contentDescription = recipe.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    androidx.compose.foundation.Image(
                        painter = painterResource(R.drawable.dish3_1),
                        contentDescription = "Recipe image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Tags/Categories badge
                if (recipe.tags.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopEnd),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MainColor.copy(alpha = 0.9f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Tags",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "45 Minutes",
                                color = Color.White,
                                fontSize = 14.sp,
                                maxLines = 1
                            )
                        }
                    }
                }
            }

            // Recipe Title
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
                color = Color(0xFF333333)
            )

            // Steps Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Steps",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = OnBoarding2Color,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    recipe.steps.forEachIndexed { index, step ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "${index + 1}.",
                                fontWeight = FontWeight.Bold,
                                color = OnBoarding2Color,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = step,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF666666),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Ingredients",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = OnBoarding2Color,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Display each ingredient with bullet points
                    recipe.ingredients.forEach { ingredient ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "•",
                                fontWeight = FontWeight.Bold,
                                color = OnBoarding2Color,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = ingredient,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF666666),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            if (recipe.tags.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Tags",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = OnBoarding2Color,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            recipe.tags.forEach { tag ->
                                Card(
                                    modifier = Modifier
                                        .padding(end = 8.dp, bottom = 8.dp)
                                        .clip(RoundedCornerShape(16.dp)),
                                    colors = CardDefaults.cardColors(containerColor = MainColor.copy(alpha = 0.2f))
                                ) {
                                    Text(
                                        text = tag,
                                        color = MainColor,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Preview function
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeDetailScreenPreview() {
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

    // For preview purposes, we can't provide a real NavController
    RecipeDetailScreen(
        navController = NavController(LocalContext.current),
        recipe = sampleRecipe
    )
}