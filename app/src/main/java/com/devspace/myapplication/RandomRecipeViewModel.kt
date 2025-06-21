package com.devspace.myapplication

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                println("🍽️ Receitas carregadas: ${randomRecipes.size}")

            } catch (e: Exception) {
//                Log.e("RecipeViewModel", "Error fetching random recipes", e)
                println("❌ Erro ao buscar receitas: ${e.message}")
            } finally {
                loading = false
            }
        }
    }
}