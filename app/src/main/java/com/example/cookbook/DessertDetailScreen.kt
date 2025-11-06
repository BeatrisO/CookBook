package com.example.cookbook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun DessertDetailScreen(mealId: String, viewModel: DessertDetailViewModel = viewModel()) {
    val mealDetail by viewModel.mealDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val hasError by viewModel.hasError.collectAsState()

    LaunchedEffect(mealId) {
        viewModel.loadMealDetail(mealId)
    }

    when {
        isLoading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        hasError -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Erro ao carregar detalhes da sobremesa")
        }

        mealDetail != null -> {
            val detail = mealDetail!!
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(detail.strMealThumb),
                    contentDescription = detail.strMeal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = detail.strMeal,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "${detail.strCategory} | ${detail.strArea}",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Preparation Method:",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = detail.strInstructions,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
