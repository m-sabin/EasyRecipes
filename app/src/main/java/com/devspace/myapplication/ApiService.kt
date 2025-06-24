package com.devspace.myapplication

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int = 20
    ): RecipeResponse

    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean = false
    ): RecipeDto

    @GET("recipes/complexSearch")
    suspend fun getRecipesList(
        @Query("query") query: String? = null,
        @Query("number") number: Int = 20
    ): RecipeListResponse

}