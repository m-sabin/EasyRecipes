package com.devspace.myapplication

class RandomRecipeRepository(
    private val apiService: ApiService = RetrofitClient
        .retrofitInstance
        .create(ApiService::class.java)
) {

    suspend fun fetchRandomRecipes(): List<RecipeDto>{
        return apiService.getRandomRecipes().recipes ?: emptyList()
    }
}

