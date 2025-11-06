package com.example.cookbook.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.Meal
import com.example.cookbook.data.repository.DessertRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DessertViewModel : ViewModel() {
    private val repository = DessertRepository()

    private val _desserts = MutableStateFlow<List<Meal>>(emptyList())
    val desserts: StateFlow<List<Meal>> = _desserts

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    init {
        loadDesserts()
    }

    private fun loadDesserts() {
        viewModelScope.launch {
            try {
                _desserts.value = repository.getDesserts()
                _isLoading.value = false
            } catch (e: Exception) {
                _hasError.value = true
                _isLoading.value = false
            }
        }
    }
}
