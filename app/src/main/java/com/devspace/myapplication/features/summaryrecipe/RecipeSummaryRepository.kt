package com.devspace.myapplication.features.summaryrecipe

import com.devspace.myapplication.common.remote.ApiService
import com.devspace.myapplication.common.dto.RecipeDto
import com.devspace.myapplication.common.remote.RetrofitClient

class RecipeSummaryRepository(
    private val apiService: ApiService = RetrofitClient
        .retrofitInstance
        .create(ApiService::class.java)
) {
    suspend fun fetchRecipeSummary(id: Int): RecipeDto {
        return apiService.getRecipeInformation(id)
    }
}