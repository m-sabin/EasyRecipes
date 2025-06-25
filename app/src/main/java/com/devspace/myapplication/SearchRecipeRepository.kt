package com.devspace.myapplication

class SearchRecipeRepository(
    private val apiService: ApiService = RetrofitClient
        .retrofitInstance
        .create(ApiService::class.java)
){
    suspend fun fetchSearchRecipes(query: String): List<SearchRecipeDto> {
        return apiService.getSearchRecipes(query).results
    }
}