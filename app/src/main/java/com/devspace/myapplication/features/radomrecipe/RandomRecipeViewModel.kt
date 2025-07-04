package com.devspace.myapplication.features.radomrecipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devspace.myapplication.common.dto.RecipeDto
import kotlinx.coroutines.launch

class RandomRecipeViewModel(
    private val repository: RandomRecipeRepository = RandomRecipeRepository()
) : ViewModel() {

    var randomRecipes by mutableStateOf<List<RecipeDto>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    init {
        getRandomRecipes()
    }

    private fun getRandomRecipes() {
        viewModelScope.launch {

            loading = true
            try {
                val recipes = repository.fetchRandomRecipes()
                randomRecipes = recipes
                println(" Loaded recipes: ${randomRecipes.size}")

            } catch (e: Exception) {
                println("Error searching for recipe: ${e.message}")
            } finally {
                loading = false
            }
        }
    }
}