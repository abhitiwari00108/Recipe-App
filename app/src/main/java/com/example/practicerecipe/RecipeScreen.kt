package com.example.practicerecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.navigation.NavController


@Composable
fun RecipeScreen(onItemClick: (Category) -> Unit, modifier: Modifier = Modifier) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    Box(modifier = modifier.fillMaxSize()) {
        when {
            viewState.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
            viewState.error != null -> Text("Error Occurred  ${viewState.error}")
            else -> {
                CategoriesScreen(viewState.recipes, onItemClick)
            }
        }
    }
}


@Composable
fun CategoriesScreen(categories: List<Category>, onItemClick: (Category) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(categories) { category ->
            CategoryItem(category, onClick = onItemClick)
        }
    }
}

@Composable
fun CategoryItem(category: Category, onClick: (Category) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(6.dp, RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(12.dp)
            .clickable{ onClick(category) }, // clickable row
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = category.strCategoryThumb,
            contentDescription = "category thumbnail",
            modifier = Modifier.size(72.dp).clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = category.strCategory,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = category.strCategoryDescription.take(30) + "...",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                maxLines = 1
            )
        }
    }
}
