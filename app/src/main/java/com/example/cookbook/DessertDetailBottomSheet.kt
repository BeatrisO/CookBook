package com.example.cookbook.ui.screens

import DessertDetailViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.data.getIngredients
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DessertDetailBottomSheet(
    mealId: String,
    navController: NavController,
    viewModel: DessertDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val mealDetail by viewModel.dessertDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val hasError by viewModel.hasError.collectAsState()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(mealId) {
        viewModel.fetchDessertDetail(mealId)
    }

    LaunchedEffect(mealDetail, hasError) {
        if (mealDetail != null || hasError) {
            scope.launch {
                if (!sheetState.isVisible) sheetState.show()
            }
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
                navController.popBackStack()
            }
        },
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {

        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            hasError -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error loading details")
                }
            }

            mealDetail != null -> {
                val detail = mealDetail!!
                val ingredients = detail.getIngredients()

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Image(
                            painter = rememberAsyncImagePainter(detail.strMealThumb),
                            contentDescription = detail.strMeal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(230.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(detail.strMeal, style = MaterialTheme.typography.headlineMedium)
                            Text("${detail.strCategory} | ${detail.strArea}",
                                style = MaterialTheme.typography.bodySmall)
                        }
                    }

                    item {
                        Text(
                            text = "Ingredients",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }

                    item {
                        val half = (ingredients.size + 1) / 2
                        val col1 = ingredients.take(half)
                        val col2 = ingredients.drop(half)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                col1.forEach { Text("• $it") }
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                col2.forEach { Text("• $it") }
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Instructions",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }

                    item {
                        Text(detail.strInstructions)
                    }
                }
            }
        }
    }
}
