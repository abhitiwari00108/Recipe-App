package com.example.practicerecipe

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)
data class categoriesResponse(val categories: List<Category>)