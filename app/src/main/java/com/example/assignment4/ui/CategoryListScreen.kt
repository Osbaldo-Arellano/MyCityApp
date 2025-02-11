/*
 * CategoryListScreen - Displays a list of categories for recommendations.
 *
 * Displays a list of recommendation categories. Allows users
 * to navigate to a selected category and its recommendations.
 * Each category is displayed with image and text.
 *
 * Features:
 * - Uses Jetpack Compose LazyColumn.
 * - Dynamically assigns images to categories.
 * - Implements Material Design 3 styles.
 * - Supports navigation to category-specific recommendation lists.
 *
 * Developed as part of Assignment 4.
 */


package com.example.assignment4.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment4.R
import androidx.compose.foundation.Image


@Composable
fun CategoryListScreen(
    categories: List<String>,
    onNextButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold() { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            items(categories) { category ->
                CategoryRow(
                    categoryName = category,
                    onClick = { onNextButtonClicked(category) }
                )
            }
        }
    }
}

@Composable
fun CategoryRow(
    categoryName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Get an image resource based on the category name
    val image = when (categoryName) {
        "Coffee Shops" -> R.drawable.coffeeshop
        "Parks"        -> R.drawable.park
        "Restaurants"  -> R.drawable.restaurant
        "Shopping"     -> R.drawable.shopping
        else           -> R.drawable.coffeeshop
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(
                horizontal = dimensionResource(R.dimen.padding_medium),
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(Color.Gray)
        ) {
            Image (
                painter = painterResource(id = image),
                contentDescription = "Category",
                modifier = Modifier.fillMaxSize(),
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Category name
        Text(
            text = categoryName,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryListScreenPreview() {
    // Sample data to preview
    val sampleCategories = listOf("Category 1", "Category 2", "Category 3", "Category 4")
    MaterialTheme {
        CategoryListScreen(
            categories = sampleCategories,
            onNextButtonClicked = {}
        )
    }
}
