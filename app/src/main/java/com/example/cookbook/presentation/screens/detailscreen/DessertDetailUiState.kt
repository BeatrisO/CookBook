package com.example.cookbook.presentation.screens.detailscreen

import com.example.cookbook.data.MealDetail

data class DessertDetailUiState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val mealDetail: MealDetail? = null
)