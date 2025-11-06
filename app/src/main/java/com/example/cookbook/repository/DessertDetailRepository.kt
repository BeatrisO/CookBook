package com.example.cookbook.data.repository

import com.example.cookbook.data.MealDetail
import com.example.cookbook.data.RetrofitInstance

class DessertDetailRepository {
    suspend fun getMealDetail(mealId: String): MealDetail? {
        val response = RetrofitInstance.api.getMealDetail(mealId)
        return response.meals.firstOrNull()
    }
}
