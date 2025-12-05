package com.example.practicerecipe

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): categoriesResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealsResponse

    @GET("search.php")
    suspend fun getRecipeByName(@Query("s") name: String): RecipeResponse

}
