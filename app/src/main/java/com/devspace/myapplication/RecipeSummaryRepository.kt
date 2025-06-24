package com.devspace.myapplication

class RecipeSummaryRepository(
    private val apiService: ApiService = RetrofitClient
        .retrofitInstance
        .create(ApiService::class.java)
) {
    suspend fun fetchRecipeSummary(id: String): RecipeDto {
        return apiService.getRecipeInformation(id)
    }
}