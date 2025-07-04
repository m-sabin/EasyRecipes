package com.devspace.myapplication.features.searchrecipe

import com.devspace.myapplication.common.remote.ApiService
import com.devspace.myapplication.common.remote.RetrofitClient

class SearchRecipeRepository(
    private val apiService: ApiService = RetrofitClient
        .retrofitInstance
        .create(ApiService::class.java)
){
    suspend fun fetchSearchRecipes(query: String): List<SearchRecipeDto> {
        return apiService.getSearchRecipes(query).results
    }
}