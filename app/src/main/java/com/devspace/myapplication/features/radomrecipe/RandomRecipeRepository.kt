package com.devspace.myapplication.features.radomrecipe

import com.devspace.myapplication.common.remote.ApiService
import com.devspace.myapplication.common.dto.RecipeDto
import com.devspace.myapplication.common.remote.RetrofitClient

class RandomRecipeRepository(
    private val apiService: ApiService = RetrofitClient
        .retrofitInstance
        .create(ApiService::class.java)
) {

    suspend fun fetchRandomRecipes(): List<RecipeDto>{
        return apiService.getRandomRecipes().recipes ?: emptyList()
    }
}

