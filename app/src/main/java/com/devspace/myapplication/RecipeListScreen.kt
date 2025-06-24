package com.devspace.myapplication

import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun RecipeListScreen(
    navController: NavHostController,
    viewModel: RandomRecipeViewModel = viewModel()
) {
    val recipes = viewModel.randomRecipes
    val isLoading = viewModel.loading

    if (isLoading) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
       RecipeDetailContent(
           recipes = recipes,
           onClick = { recipe ->
               navController.navigate("RecipeSummary/${recipe.id}")

           }
       )
    }
}

@Composable
fun HtmlTextViewBySummary(htmlText: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
            textSize = 14f
            maxLines = 3
        }
    })
}

@Composable
private fun RecipeDetailContent(
    recipes: List<RecipeDto>,
    onClick: (RecipeDto) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        RecipeSession(
            label = "Recipes",
            recipes = recipes,
            oncClick = onClick
        )
    }

}

@Composable
private fun RecipeSession(
    label: String,
    recipes: List<RecipeDto>,
    oncClick: (RecipeDto) -> Unit
) {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        text = label
    )
    RecipeList(
        recipes = recipes,
        onClick = oncClick
    )
}


@Composable
private fun RecipeList(
    recipes: List<RecipeDto>,
    onClick: (RecipeDto) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(recipes) {
            RecipeItem(recipe = it, onClick)
        }
    }
}

@Composable
private fun RecipeItem(
    recipe: RecipeDto,
    oncClick: (RecipeDto) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                oncClick.invoke(recipe)
            }
    ) {
        Text(
            text = recipe.title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,

            )
        Spacer(modifier = Modifier.size(8.dp))

        HtmlTextViewBySummary(
            htmlText = recipe.summary
        )

        Spacer(modifier = Modifier.size(16.dp))

        AsyncImage(
            model = recipe.image,
            contentDescription = "image recipe",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}