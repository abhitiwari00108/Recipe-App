package com.example.practicerecipe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.State


class MainViewModel(private val repository: RecipeRepository = RecipeRepository(recipeService)): ViewModel() {

    val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState : State<RecipeState> = _categoriesState


    init{
        fetchCategories()
    }
    private fun fetchCategories() {

        viewModelScope.launch {
            try {
                val response = repository.getCategories()
                _categoriesState.value= _categoriesState.value.copy(
                    isLoading = false,
                    recipes = response.categories,
                    error = null
                )
            }catch (e: Exception){
                _categoriesState.value = _categoriesState.value.copy(
                    isLoading = false,
                    recipes = emptyList(),
                    error = "not able to fetch categories because of ${e.message}"
                )
            }
        }

    }





    data class RecipeState(
        val isLoading: Boolean = true,
        val recipes: List<Category> = emptyList(),
        val error: String? = null
    )
}