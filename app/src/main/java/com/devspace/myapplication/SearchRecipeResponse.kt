package com.devspace.myapplication

data class SearchRecipeDto(
    val id: Int,
    val title: String,
    val image: String,
)

data class SearchRecipeResponse(
    val results: List<SearchRecipeDto>,
    val offset: Int,
    val number: Int,
    val totalResults: Int
)
