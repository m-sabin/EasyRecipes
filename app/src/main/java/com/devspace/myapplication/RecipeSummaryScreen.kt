package com.devspace.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devspace.myapplication.ui.theme.EasyRecipesTheme

@Composable
fun RecipeSummaryScreen(
    id: String,
    navController: NavHostController,
    viewModel: RecipeSummaryViewModel = viewModel()
) {
    val recipe = viewModel.recipeSummary
    val laoding = viewModel.loading

    LaunchedEffect(id) {
        viewModel.getRecipeSummary(id)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )

                recipe.let {
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = ""
                    )
                }
                when {
                    laoding -> Text("🔄 Carregando receita...")
                    recipe != null -> RecipeSummaryContent(recipe)
                    else -> Text("❌ Receita não encontrada")
                }
            }
        }
    }
}

@Composable
fun RecipeSummaryContent(recipe: RecipeDto) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            model = recipe.image,
            contentDescription = "${recipe.title} image",
            contentScale = ContentScale.Crop,
        )

        HtmlTextViewBySummary(recipe.summary)
    }
}