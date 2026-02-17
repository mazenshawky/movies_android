package com.example.moviesandroid.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val CinemaDarkColorScheme = darkColorScheme(
    primary = OrangeAccent,
    onPrimary = TextWhite,
    secondary = OrangeAccentLight,
    background = DarkBackground,
    surface = DarkSurface,
    onBackground = TextWhite,
    onSurface = TextWhite
)

@Composable
fun MoviesAndroidTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CinemaDarkColorScheme,
        typography = Typography,
        content = content
    )
}