package com.devspace.myapplication.common.remote

import com.devspace.myapplication.common.dto.RecipeDto
import com.devspace.myapplication.common.dto.RecipeResponse
import com.devspace.myapplication.features.searchrecipe.SearchRecipeResponse
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
    suspend fun getSearchRecipes(@Query("query") query: String) : SearchRecipeResponse

}