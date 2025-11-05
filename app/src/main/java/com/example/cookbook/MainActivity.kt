package com.example.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.ui.screens.DessertDetailScreen
import com.example.cookbook.ui.screens.DessertListScreen
import com.example.cookbook.ui.theme.CookBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookTheme {
                val navController = rememberNavController()

                Box(modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues())) {
                    NavHost(
                        navController = navController,
                        startDestination = "dessert_list"
                    ) {
                        composable("dessert_list") {
                            DessertListScreen { mealId ->
                                navController.navigate("dessert_detail/$mealId")
                            }
                        }

                        composable("dessert_detail/{mealId}") { backStackEntry ->
                            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
                            DessertDetailScreen(mealId = mealId)
                        }
                    }
                }
            }
        }
    }
}
