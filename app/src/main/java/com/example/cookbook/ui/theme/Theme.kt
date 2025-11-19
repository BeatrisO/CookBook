package com.example.cookbook.ui.theme

import android.app.Activity
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

private val LightColors = lightColorScheme(
    primary = Color(0xFFFF8A80),
    onPrimary = Color.White,
    secondary = Color(0xFFFFD180),
    onSecondary = Color.Black,
    background = Color(0xFFFFF8F0),
    surface = Color.White,
    onSurface = Color(0xFF4E342E)
)

@Composable
fun CookBookTheme(content: @Composable () -> Unit) {

    val activity = LocalContext.current as Activity

    SideEffect {
        val window = activity.window

        WindowCompat.setDecorFitsSystemWindows(window, true)

        window.statusBarColor = LightColors.primary.toArgb()

        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = true
            LightColors.primary.luminance() > 0.5
    }

    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(),
        content = content
    )
}

