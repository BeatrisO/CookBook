package com.example.cookbook.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.repository.DessertRepository
import com.example.cookbook.presentation.screens.listscreen.DessertUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DessertViewModel(
    private val repository: DessertRepository = DessertRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState(isLoading = true))
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    init {
        loadDesserts()
    }

    private fun loadDesserts() {
        viewModelScope.launch {
            try {
                val desserts = repository.getDesserts()

                _uiState.value = DessertUiState(
                    isLoading = false,
                    hasError = false,
                    desserts = desserts
                )

            } catch (e: Exception) {
                _uiState.value = DessertUiState(
                    isLoading = false,
                    hasError = true,
                    desserts = emptyList()
                )
            }
        }
    }
}