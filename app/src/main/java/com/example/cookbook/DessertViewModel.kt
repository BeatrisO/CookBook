package com.example.cookbook.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.Meal
import com.example.cookbook.data.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DessertViewModel : ViewModel() {

    private val _desserts = MutableStateFlow<List<Meal>>(emptyList())
    val desserts: StateFlow<List<Meal>> = _desserts

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    init {
        fetchDesserts()
    }

    private fun fetchDesserts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitInstance.api.getDesserts()
                _desserts.value = response.meals ?: emptyList()
            } catch (e: Exception) {
                _hasError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}
