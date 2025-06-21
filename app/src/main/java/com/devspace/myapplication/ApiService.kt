package com.devspace.myapplication

import retrofit2.http.GET

interface ApiService {

    @GET("recipes/random?number=1&include-tags=vegetarian,dessert&exclude-tags=quinoa")
    suspend fun getRandomRecipes(): RecipeResponse
}