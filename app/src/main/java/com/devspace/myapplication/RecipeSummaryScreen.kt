package com.devspace.myapplication

import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devspace.myapplication.ui.theme.EasyRecipesTheme

@Composable
fun RecipeSummaryScreen(
    id: Int,
    navController: NavHostController,
    viewModel: RecipeSummaryViewModel = viewModel()
) {
    val recipe = viewModel.recipeSummary
    val loading = viewModel.loading

    LaunchedEffect(id) {
        viewModel.getRecipeSummary(id)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)

    ) {
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
            }

            recipe.let {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    fontWeight = FontWeight.SemiBold,
                    text = recipe?.title ?: "loading"
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        when {
            loading -> {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            recipe != null -> {
                RecipeSummaryContent(recipe)
            }

            else -> {
                Text("❌ Receita não encontrada")
            }
        }

    }
}
@Composable
fun HtmlTextViewByInformation(htmlText: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
            textSize = 14f
        }
    })
}

@Composable
fun RecipeSummaryContent(recipe: RecipeDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            model = recipe.image,
            contentDescription = "${recipe.title} image",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.size(8.dp))
        HtmlTextViewByInformation(recipe.summary)
    }
}