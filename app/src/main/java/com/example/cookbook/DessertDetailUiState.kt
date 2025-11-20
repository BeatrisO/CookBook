package com.example.cookbook.ui.screens

import com.example.cookbook.data.MealDetail

data class DessertDetailUiState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val mealDetail: MealDetail? = null
)
