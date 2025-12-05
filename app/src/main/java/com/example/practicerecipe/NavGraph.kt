package com.example.practicerecipe

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.RecipeList.route
    ) {
        // Categories list
        composable(Screen.RecipeList.route) {
            RecipeScreen ({ category ->
                // Navigate to CategoryMeals when a category is clicked
                navController.navigate(Screen.CategoryMeals.createRoute(category.strCategory))
            })
        }

        // Meals in a category
        composable(
            route = Screen.CategoryMeals.route,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            CategoryMealsScreen(
                categoryName = categoryName,
                onMealClick = { recipeName ->
                    navController.navigate(Screen.RecipeDetail.createRoute(recipeName))
                }
            )
        }

        // Recipe detail (by recipeName, unchanged)
        composable(
            route = Screen.RecipeDetail.route,
            arguments = listOf(navArgument("recipeName") { type = NavType.StringType })
        ) { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName")
            RecipeDetailScreen(recipeName)
        }
    }
}

