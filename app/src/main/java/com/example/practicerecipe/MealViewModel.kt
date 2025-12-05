package com.example.practicerecipe

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoryMealsViewModel(
    private val repository: RecipeRepository = RecipeRepository(recipeService)
) : ViewModel() {

    private val _mealsState = mutableStateOf(MealsState())
    val mealsState: State<MealsState> = _mealsState

    fun loadMeals(categoryName: String) {
        viewModelScope.launch {
            _mealsState.value = MealsState(isLoading = true)
            try {
                val response = repository.getMealsByCategory(categoryName)
                _mealsState.value = MealsState(
                    isLoading = false,
                    meals = response.meals
                )
            } catch (e: Exception) {
                _mealsState.value = MealsState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    data class MealsState(
        val isLoading: Boolean = true,
        val meals: List<MealSummary> = emptyList(),
        val error: String? = null
    )
}
