package com.example.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.cookbook.ui.theme.CookBookTheme
import com.example.cookbook.ui.screens.DessertListScreen
import com.example.cookbook.ui.screens.DessertDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookApp()
        }
    }
}

@Composable
fun CookBookApp() {
    CookBookTheme {
        val navController = rememberNavController()

        Box(modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues())) {
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
