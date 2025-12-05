package com.example.practicerecipe

class RecipeRepository(private val api: ApiService) {
    suspend fun getCategories(): categoriesResponse = api.getCategories()

    suspend fun getMealsByCategory(category: String): MealsResponse = api.getMealsByCategory(category)

    suspend fun getRecipeByName(name: String): RecipeResponse = api.getRecipeByName(name)
}
