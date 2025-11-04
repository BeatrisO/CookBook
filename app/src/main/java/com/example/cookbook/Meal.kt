package com.example.cookbook.data

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

data class MealResponse(
    val meals: List<Meal>
)

data class MealDetail(
    val idMeal: String,
    val strMeal: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strCategory: String,
    val strArea: String
)

data class MealDetailResponse(
    val meals: List<MealDetail>
)
