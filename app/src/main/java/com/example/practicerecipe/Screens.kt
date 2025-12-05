package com.example.practicerecipe

sealed class Screen(val route: String) {
    object RecipeList: Screen("recipe_list")

    object CategoryMeals : Screen("category_meals/{categoryName}") {
        fun createRoute(categoryName: String) = "category_meals/$categoryName"
    }
    object RecipeDetail: Screen("recipe_detail/{recipeName}"){
        fun createRoute(recipeName: String) = "recipe_detail/$recipeName"
    }
}

