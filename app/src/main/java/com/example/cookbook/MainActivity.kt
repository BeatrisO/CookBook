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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cookbook.ui.screens.DessertListScreen
import com.example.cookbook.ui.screens.DessertDetailScreen
import com.example.cookbook.ui.theme.CookBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookTheme {
                Box(modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues())) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "dessertList"
                    ) {
                        composable("dessertList") {
                            DessertListScreen(
                                onMealClick = { mealId ->
                                    navController.navigate("dessertDetail/$mealId")
                                }
                            )
                        }

                        composable(
                            route = "dessertDetail/{mealId}",
                            arguments = listOf(navArgument("mealId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
                            DessertDetailScreen(mealId = mealId, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
