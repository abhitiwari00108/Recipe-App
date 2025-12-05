package com.example.practicerecipe

data class MealSummary(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

data class MealsResponse(
    val meals: List<MealSummary>
)
