package com.example.cookbook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.data.Meal
import com.example.cookbook.data.RetrofitInstance

@Composable
fun DessertListScreen(onMealClick: (String) -> Unit) {
    var desserts by remember { mutableStateOf<List<Meal>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            desserts = RetrofitInstance.api.getDesserts().meals
            isLoading = false
        } catch (e: Exception) {
            hasError = true
            isLoading = false
        }
    }

    when {
        isLoading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        hasError -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Error loading desserts")
        }
        else -> LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(desserts) { dessert ->
                DessertCard(dessert) {
                    onMealClick(dessert.idMeal)
                }
            }
        }
    }
}

@Composable
fun DessertCard(meal: Meal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(meal.strMealThumb),
                contentDescription = meal.strMeal,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = meal.strMeal,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DessertListScreenPreview() {
    val mockDesserts = listOf(
        Meal(
            idMeal = "1",
            strMeal = "Bolo de Chocolate",
            strMealThumb = "https://www.themealdb.com/images/media/meals/tkxquw1628771028.jpg"
        ),
        Meal(
            idMeal = "2",
            strMeal = "Bolo de Morango",
            strMealThumb = "https://www.themealdb.com/images/media/meals/tvtxpq1511464705.jpg"
        ),
        Meal(
            idMeal = "3",
            strMeal = "Cheesecake",
            strMealThumb = "https://www.themealdb.com/images/media/meals/qyqtpv1511463010.jpg"
        )
    )
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(mockDesserts) { dessert ->
            DessertCard(meal = dessert, onClick = {})
        }
    }
}
