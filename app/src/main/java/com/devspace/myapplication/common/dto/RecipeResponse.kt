package com.devspace.myapplication.common.dto

data class RecipeResponse(
    val recipes: List<RecipeDto>? = emptyList()
)