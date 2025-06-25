package com.devspace.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import retrofit2.http.Query

@Composable
fun SearchRecipeScreen(
    query: String,
    navController: NavController,
    viewModel: SearchRecipeViewModel = viewModel()
) {
    val recipes = viewModel.searchRecipes
    val loading = viewModel.loading

    LaunchedEffect(query) {
        viewModel.getSearchRecipes(query = String())
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {navController.popBackStack()}
            ){
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
          Text(
              modifier = Modifier.padding(start = 4.dp),
              text = query
          )
        }

        if (loading) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
                SearchRecipeContent(
                    recipesList = recipes,
                    onClick = {itemClicked ->
                        navController.navigate("SearchRecipe/${itemClicked.id}")
                    })
            }



    }
}

@Composable
private fun SearchRecipeContent(
    recipesList: List<SearchRecipeDto>,
    onClick: (SearchRecipeDto) -> Unit
) {
    SearchRecipeList(recipesList, onClick)
}

@Composable
private fun SearchRecipeList(
    recipes: List<SearchRecipeDto>,
    onClick: (SearchRecipeDto) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(recipes) {
            SearchRecipeItem(searchRecipes = it, onClick = onClick)
        }
    }
}

@Composable
private fun SearchRecipeItem(
    searchRecipes: SearchRecipeDto,
    onClick: (SearchRecipeDto) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick.invoke(searchRecipes)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp))
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop,
            model = searchRecipes.image, contentDescription = "${searchRecipes.title} image",

            )
    }
}

