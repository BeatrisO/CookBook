package com.example.cookbook.ui.theme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColors = lightColorScheme(
    primary = Color(0xFFFF8A80),
    onPrimary = Color.White,
    secondary = Color(0xFFFFD180),
    onSecondary = Color.Black,
    background = Color(0xFFFFF8F0),
    surface = Color.White,
    onSurface = Color(0xFF4E342E),
)
@Composable
fun CookBookTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}
