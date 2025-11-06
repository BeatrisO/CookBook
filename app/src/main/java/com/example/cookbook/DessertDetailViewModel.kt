package com.example.cookbook.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.MealDetail
import com.example.cookbook.data.repository.DessertDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DessertDetailViewModel : ViewModel() {
    private val repository = DessertDetailRepository()

    private val _mealDetail = MutableStateFlow<MealDetail?>(null)
    val mealDetail: StateFlow<MealDetail?> = _mealDetail

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    fun loadMealDetail(mealId: String) {
        viewModelScope.launch {
            try {
                _mealDetail.value = repository.getMealDetail(mealId)
                _isLoading.value = false
            } catch (e: Exception) {
                _hasError.value = true
                _isLoading.value = false
            }
        }
    }
}
