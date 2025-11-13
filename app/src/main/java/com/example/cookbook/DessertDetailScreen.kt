package com.example.cookbook.ui.screens

import DessertDetailViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.navigation.NavController
import com.example.cookbook.data.getIngredients


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DessertDetailScreen(
    mealId: String,
    navController: NavController,
    viewModel: DessertDetailViewModel = DessertDetailViewModel()
) {
    val mealDetail by viewModel.dessertDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val hasError by viewModel.hasError.collectAsState()

    LaunchedEffect(mealId) {
        viewModel.fetchDessertDetail(mealId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dessert Details") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = Modifier.height(48.dp),
                windowInsets = WindowInsets(0, 0, 0, 0)
            )
        }
    )

    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
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
                    Text("Error loading dessert details")
                }

                mealDetail != null -> {
                    val detail = mealDetail!!
                    val ingredientsList = detail.getIngredients()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        item {
                            Image(
                                painter = rememberAsyncImagePainter(detail.strMealThumb),
                                contentDescription = detail.strMeal,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                            )
                        }

                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = detail.strMeal,
                                    style = MaterialTheme.typography.headlineMedium,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "${detail.strCategory} | ${detail.strArea}",
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        item {
                            Text(
                                text = "Ingredients:",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                        item {
                            val half = (ingredientsList.size + 1) / 2
                            val column1 = ingredientsList.take(half)
                            val column2 = ingredientsList.drop(half)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    column1.forEach { ingredient ->
                                        Text(
                                            text = "• $ingredient",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    column2.forEach { ingredient ->
                                        Text(
                                            text = "• $ingredient",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            }
                        }

                        item {
                            Text(
                                text = "Instructions:",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                        item {
                            Text(
                                text = detail.strInstructions,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
