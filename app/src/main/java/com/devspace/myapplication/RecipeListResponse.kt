package com.devspace.myapplication

data class RecipeListResponse(
    val results: List<RecipeListItem>,
    val offset: Int,
    val number: Int,
    val totalResults: Int
)

data class RecipeListItem(
    val id: Int,
    val title: String,
    val image: String,
    val imageType: String
)