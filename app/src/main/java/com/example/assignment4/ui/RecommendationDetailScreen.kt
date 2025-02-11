/*
 * RecommendationDetailScreen - Displays detailed information about a selected place.
 *
 * Displays an image, description, and address for the selected
 * recommendation. It allows users to view more details about a place and navigate back
 *
 * Features:
 * - Uses Jetpack Compose.
 * - Dynamically loads images based on the selected place.
 * - Uses Material Design 3 styles.
 * - Supports vertical scrolling when the phone is flipped sideways.
 *
 * Developed as part of Assignment 4.
 */


package com.example.assignment4.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.assignment4.R

@Composable
fun RecommendationDetailScreen(
    places: Map<String, Pair<String, String>>,
    selectedPlace: String,
    onNextButtonClicked: (String) -> Unit,
    onBackButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Get details and image resource based on the selected place
    val details = places[selectedPlace]
    val imageRes = getImageRes(selectedPlace)

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                // SOURCE: https://developer.android.com/develop/ui/compose/touch-input/pointer-input/scroll
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium),
                    vertical = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = selectedPlace,
                style = MaterialTheme.typography.bodyLarge
            )

            // Display the image dynamically based on selectedPlace
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = selectedPlace,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            if (details != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = details.first,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Address:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    Text(
                        text = details.second,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }


            } else {
                Text(
                    text = "No details available.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        }
    }
}

private fun getImageRes(placeName: String): Int {
    return when (placeName) {
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
}
