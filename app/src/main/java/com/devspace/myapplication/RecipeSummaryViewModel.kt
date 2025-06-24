package com.devspace.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.math.log

class RecipeSummaryViewModel(
    private val repository: RecipeSummaryRepository = RecipeSummaryRepository()
) : ViewModel() {
    var recipeSummary by mutableStateOf<RecipeDto?>(null)
        private set

    var loading by mutableStateOf(false)
        private set

     fun getRecipeSummary(id: String) {
        viewModelScope.launch {
            loading = true
            try {
                val recipe = repository.fetchRecipeSummary(id)
                recipeSummary = recipe
            } catch (e: Exception) {
                println("❌ Erro ao buscar receitas: ${e.message}")
            } finally {
                loading = false
            }
        }
    }
}

