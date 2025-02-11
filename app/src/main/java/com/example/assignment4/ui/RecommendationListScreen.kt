/*
 * RecommendationListScreen - Displays a list of recommended places within a selected category.
 *
 * Shows a list of recommendations based on the chosen category.
 * Each recommendation is displayed with an image and a row that is clickable
 * that allows the user to navigate to a details screen of the selected place.
 *
 * Features:
 * - Uses Jetpack Compose LazyColumn for list rendering.
 * - Dynamically assigns images to each Composable.
 * - Implements Material Design 3 styling for UI.
 *
 * Developed as part of Assignment 4.
 */


package com.example.assignment4.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.assignment4.R

@Composable
fun RecommendationListScreen(
    categories: Map<String, List<String>>,
    selectedCategory: String,
    onNextButtonClicked: (String) -> Unit,
    onBackButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Get the list of places for the selected category.
    val recommendations = categories[selectedCategory] ?: emptyList()

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            items(recommendations) { place ->
                RecommendationRow(
                    placeName = place,
                    onClick = { onNextButtonClicked(place) }
                )
                Divider()
            }
        }
    }
}

@Composable
fun RecommendationRow(
    placeName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    // Get an image resource based on the place name
    val image = getImageRes(placeName)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_medium),
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image container styled similarly to CategoryRow.
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(Color.Gray)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Place Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Display the recommendation (place) name.
        Text(
            text = placeName,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private fun getImageRes(placeName: String): Int {
    val image = when (placeName) {
        "Café La Onda"                  -> R.drawable.laonda
        "Starbucks"                     -> R.drawable.starbucks
        "Dutch Bros"                    -> R.drawable.dutch
        "Panera"                        -> R.drawable.panera
        "Heritage Park"                 -> R.drawable.heritage
        "Willamette Mission State Park" -> R.drawable.missionpark
        "Settlemier Park"               -> R.drawable.settle
        "Legion Park"                   -> R.drawable.legion
        "Casa De Caldos"                -> R.drawable.caldo
        "Las Islas"                     -> R.drawable.islas
        "Luis's Taqueria"               -> R.drawable.luisstaqueria
        "The Pierogi Place"             -> R.drawable.pierogiplace
        else                            -> R.drawable.coffeeshop
    }

    return image
}
