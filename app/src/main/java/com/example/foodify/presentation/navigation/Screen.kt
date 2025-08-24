package com.example.foodify.presentation.navigation

sealed class Screen(val route:String) {
    object Home: Screen("home_screen")
    object Splash:Screen("splash_screen")
    object OnBoarding: Screen("onboarding_screen")
    object CollectionDetail : Screen("collection_detail")
    object Search : Screen("search")
    object Add : Screen("add")
    object Save : Screen("save")
    object RecipeDetail : Screen("recipe_detail")
    object Profile : Screen("profile")


}