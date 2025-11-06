package com.example.cookbook.data.repository

import com.example.cookbook.data.Meal
import com.example.cookbook.data.RetrofitInstance

class DessertRepository {
    suspend fun getDesserts(): List<Meal> {
        return RetrofitInstance.api.getDesserts().meals
    }
}
