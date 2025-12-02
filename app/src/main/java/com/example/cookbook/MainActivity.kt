package com.example.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.cookbook.presentation.screens.detailscreen.DessertDetailBottomSheet
import com.example.cookbook.presentation.theme.CookBookTheme
import com.example.cookbook.presentation.screens.listscreen.DessertListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CookBookApp()
        }
    }
}

@Composable
fun CookBookApp() {
    CookBookTheme {
        val navController = rememberNavController()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            NavHost(
                navController = navController,
                startDestination = "dessertList"
            ) {
                composable("dessertList") {
                    DessertListScreen(
                        onMealClick = { mealId ->
                            navController.navigate("detailsSheet/$mealId")
                        }
                    )
                }

                composable(
                    route = "detailsSheet/{mealId}",
                    arguments = listOf(navArgument("mealId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
                    DessertDetailBottomSheet(mealId = mealId, navController = navController)
                }
            }
        }
    }
}

