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
                type = NavType.StringType
            })
        ) { backstackEntry ->
            val id = requireNotNull(backstackEntry.arguments?.getString("recipeId"))
            RecipeSummaryScreen(id, navController )

        }
    }
}