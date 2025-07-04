package com.devspace.myapplication.features.searchrecipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchRecipeViewModel(
    private val repository: SearchRecipeRepository = SearchRecipeRepository()
) : ViewModel() {

    var searchRecipes by mutableStateOf<List<SearchRecipeDto>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set


    fun getSearchRecipes(query: String) {
        viewModelScope.launch {
            loading = true
            try {
                val recipes = repository.fetchSearchRecipes(query)
                searchRecipes = recipes

            } catch (e: Exception) {
                println("error searching for recipe: ${e.message}")
            } finally {
                loading = false
            }

        }
    }

}