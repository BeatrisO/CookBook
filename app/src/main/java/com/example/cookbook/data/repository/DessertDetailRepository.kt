package com.example.cookbook.data.repository

import com.example.cookbook.data.MealDetail
import com.example.cookbook.data.remote.RetrofitInstance

class DessertDetailRepository {

    suspend fun getDessertDetail(mealId: String): MealDetail? {
        return RetrofitInstance.api.getMealDetail(mealId).meals.firstOrNull()
    }
}
