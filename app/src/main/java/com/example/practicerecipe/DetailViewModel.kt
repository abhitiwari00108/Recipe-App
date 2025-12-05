package com.example.practicerecipe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.State


class RecipeDetailViewModel(
    private val repository: RecipeRepository = RecipeRepository(recipeService)
) : ViewModel() {

    private val _recipeDetailState = mutableStateOf(RecipeDetailState())
    val recipeDetailState: State<RecipeDetailState> = _recipeDetailState

    fun loadRecipeDetail(recipeName: String) {
        viewModelScope.launch {
            _recipeDetailState.value = RecipeDetailState(isLoading = true)
            try {
                val response = repository.getRecipeByName(recipeName)
                val recipe = response.meals?.firstOrNull()
                _recipeDetailState.value = RecipeDetailState(
                    isLoading = false,
                    recipe = recipe
                )
            } catch (e: Exception) {
                _recipeDetailState.value = RecipeDetailState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    data class RecipeDetailState(
        val isLoading: Boolean = true,
        val recipe: Recipe? = null,
        val error: String? = null
    )
}
