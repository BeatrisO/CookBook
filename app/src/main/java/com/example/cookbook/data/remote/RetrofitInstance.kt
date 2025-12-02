package com.example.cookbook.data.remote

import com.example.cookbook.data.MealApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MealApi by lazy {
        retrofit.create(MealApi::class.java)
    }
}