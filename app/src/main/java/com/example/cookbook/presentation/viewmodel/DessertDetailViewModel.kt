package com.example.cookbook.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.repository.DessertDetailRepository
import com.example.cookbook.presentation.screens.detailscreen.DessertDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DessertDetailViewModel(
    private val repository: DessertDetailRepository = DessertDetailRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(DessertDetailUiState(isLoading = true))
    val uiState: StateFlow<DessertDetailUiState> = _uiState.asStateFlow()

    fun loadDessertDetail(mealId: String) {
        viewModelScope.launch {

            _uiState.value = DessertDetailUiState(isLoading = true)

            try {
                val detail = repository.getDessertDetail(mealId)

                if (detail != null) {
                    _uiState.value = DessertDetailUiState(
                        isLoading = false,
                        hasError = false,
                        mealDetail = detail
                    )
                } else {
                    _uiState.value = DessertDetailUiState(
                        isLoading = false,
                        hasError = true
                    )
                }

            } catch (e: Exception) {
                _uiState.value = DessertDetailUiState(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }
}