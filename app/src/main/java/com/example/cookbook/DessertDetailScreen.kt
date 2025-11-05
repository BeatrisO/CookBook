package com.example.cookbook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.data.MealDetail
import com.example.cookbook.data.RetrofitInstance

@Composable
fun DessertDetailScreen(mealId: String) {
    var mealDetail by remember { mutableStateOf<MealDetail?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }

    LaunchedEffect(mealId) {
        try {
            val response = RetrofitInstance.api.getMealDetail(mealId)
            mealDetail = response.meals.firstOrNull()
            isLoading = false
        } catch (e: Exception) {
            hasError = true
            isLoading = false
        }
    }

    when {
        isLoading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        hasError -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Erro ao carregar detalhes da sobremesa")
        }

        else -> mealDetail?.let { detail ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = rememberAsyncImagePainter(detail.strMealThumb),
                    contentDescription = detail.strMeal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                Spacer(Modifier.height(12.dp))

                Text(
                    text = detail.strMeal,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(Modifier.height(8.dp))

                Text("Categoria: ${detail.strCategory}")
                Text("Origem: ${detail.strArea}")
                Spacer(Modifier.height(16.dp))

                Text(
                    text = detail.strInstructions,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
