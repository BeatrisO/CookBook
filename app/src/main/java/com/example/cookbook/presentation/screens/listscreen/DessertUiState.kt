package com.example.cookbook.presentation.screens.listscreen

import com.example.cookbook.data.Meal

data class DessertUiState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val desserts: List<Meal> = emptyList()
)