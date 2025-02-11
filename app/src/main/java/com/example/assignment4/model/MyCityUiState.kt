/*
 * MyCityUiState - Data class containing the UI state of the app.
 *
 * This object is as a simple database containing categories,
 * lists of recommended places, and detailed information about each place.
 *
 * Structure:
 * - `selectedCategoryId`: A selectable category from a recommendation list.
 * - `selectedPlaceId`: A selectable place from a category list.
 *
 * Developed as part of Assignment 4.
 */

package com.example.assignment4.model

data class MyCityUiState(
    val selectedCategoryId: String,
    val selectedPlaceId: String? = null,
)
