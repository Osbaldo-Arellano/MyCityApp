/*
 * CityViewModel - ViewModel for managing MyCity app state.
 *
 * This ViewModel maintains and updates the UI state of the app.
 * It follows the Unidirectional Data Flow (UDF) pattern
 *
 * Features:
 * - Tracks the currently selected category and place.
 * - Methods to update selections.
 * - Resetting selections to the initial state.
 *
 * Developed as part of Assignment 4.
 */

package com.example.assignment4.ui.theme

import androidx.lifecycle.ViewModel
import com.example.assignment4.model.MyCityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CityViewModel : ViewModel () {
    private val _uiState = MutableStateFlow(MyCityUiState(selectedCategoryId = ""))

    val uiState: StateFlow<MyCityUiState> = _uiState

    fun selectCategory(categoryId: String) {
        _uiState.value = _uiState.value.copy(selectedCategoryId = categoryId)
    }

    fun selectPlace(placeId: String) {
        _uiState.value = _uiState.value.copy(selectedPlaceId = placeId)
    }

    fun resetSelections() {
        _uiState.value = MyCityUiState(selectedCategoryId = "", selectedPlaceId = "")
    }
}