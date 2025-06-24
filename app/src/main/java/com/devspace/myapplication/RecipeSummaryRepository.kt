package com.devspace.myapplication

class RecipeSummaryRepository(
    private val apiService: ApiService = RetrofitClient
        .retrofitInstance
        .create(ApiService::class.java)
) {
    suspend fun fetchRecipeSummary(id: Int): RecipeDto {
        return apiService.getRecipeInformation(id)
    }
}