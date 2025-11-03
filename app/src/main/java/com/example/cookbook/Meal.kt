package com.example.cookbook.data

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

data class MealResponse(
    val meals: List<Meal>
)
