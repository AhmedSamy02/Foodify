package com.example.foodify.presentation.navigation

sealed class Screen(val route:String) {
    object Home: Screen("home_screen")
    object Splash:Screen("splash_screen")
    object OnBoarding: Screen("onboarding_screen")

    object Collections : Screen("collections_screen")
    object CollectionDetail : Screen("collection_detail_screen/{collectionId}") {
        fun createRoute(collectionId: Int) = "collection_detail_screen/$collectionId"
    }
    object RecipeDetail : Screen("recipe_detail_screen/{recipeId}") {
        fun createRoute(recipeId: Int) = "recipe_detail_screen/$recipeId"
    }
}