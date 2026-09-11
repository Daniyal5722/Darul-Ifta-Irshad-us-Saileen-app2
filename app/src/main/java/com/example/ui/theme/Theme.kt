package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme =
  darkColorScheme(
    primary = IslamicGoldBright,
    onPrimary = IslamicNavyDark,
    primaryContainer = IslamicNavyMedium,
    onPrimaryContainer = IslamicGoldLight,
    secondary = IslamicBlueLight,
    onSecondary = TextOnDark,
    tertiary = IslamicGold,
    background = DarkNavyBg,
    onBackground = DarkTextPrimary,
    surface = DarkNavySurface,
    onSurface = DarkTextPrimary,
    surfaceVariant = DarkNavyCard,
    onSurfaceVariant = DarkTextSecondary,
    outline = DarkNavyBorder,
  )

private val LightColorScheme =
  lightColorScheme(
    primary = IslamicNavyDark,
    onPrimary = TextOnDark,
    primaryContainer = IslamicNavyMedium,
    onPrimaryContainer = IslamicGoldLight,
    secondary = IslamicBlue,
    onSecondary = TextOnDark,
    tertiary = IslamicGold,
    onTertiary = TextOnDark,
    background = ParchmentBg,
    onBackground = TextPrimary,
    surface = CardWhite,
    onSurface = TextPrimary,
    surfaceVariant = ParchmentSurface,
    onSurfaceVariant = TextSecondary,
    outline = ParchmentBorder,
  )

@Composable
fun DarulIftaTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

