package com.example.cookbook.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("filter.php?c=Dessert")
    suspend fun getDesserts(): MealResponse

    @GET("lookup.php")
    suspend fun getMealDetail(
        @Query("i") mealId: String): MealDetailResponse
}
