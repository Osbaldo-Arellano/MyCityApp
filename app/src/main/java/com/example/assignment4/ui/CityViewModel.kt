package com.example.assignment4.ui.theme

import androidx.lifecycle.ViewModel
import com.example.mycity.data.MyCityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CityViewModel : ViewModel () {
    private val _uiState = MutableStateFlow(MyCityUiState())

    val uiState: StateFlow<MyCityUiState> = _uiState

    fun selectCategory(categoryId: String) {
        _uiState.value = _uiState.value.copy(selectedCategoryId = categoryId)
    }

    fun selectPlace(placeId: String) {
        _uiState.value = _uiState.value.copy(selectedPlaceId = placeId)
    }

    fun resetSelections() {
        _uiState.value = MyCityUiState()
    }


}