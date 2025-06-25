package com.devspace.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun RecipeApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "onboarding_screen") {
        composable(route = "onboarding_screen") {
            OnboardingScreen(navController)
        }

        composable(route = "RecipeListScreen") {
            RecipeListScreen(navController)
        }

        composable(
            route = "RecipeSummary/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { backstackEntry ->
            val id = requireNotNull(backstackEntry.arguments?.getInt("recipeId"))
            RecipeSummaryScreen(id, navController )

        }

        composable(
            route = "SearchRecipe/{query}",
            arguments = listOf(navArgument("query"){
                type = NavType.StringType
            })
        ) { backstackEntry ->
            val query = requireNotNull(backstackEntry.arguments?.getString("query"))
            SearchRecipeScreen(query, navController)
        }
    }
}