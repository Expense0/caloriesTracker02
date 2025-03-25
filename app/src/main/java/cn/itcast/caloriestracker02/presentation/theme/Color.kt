package cn.itcast.caloriestracker02.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// 深色方案（补充surface/error等必要色槽）
internal val DarkColors = darkColorScheme(
    primary = Color(0xFF81C784),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF388E3C),
    secondary = Color(0xFF64B5F6),
    tertiary = Color(0xFFBA68C8),
    surface = Color(0xFF121212),
    background = Color(0xFF000000),
    error = Color(0xFFCF6679),
    // 其他色槽保持默认值...
)

// 浅色方案
internal val LightColors = lightColorScheme(
    primary = Color(0xFF2E7D32),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFA5D6A7),
    secondary = Color(0xFF2196F3),
    tertiary = Color(0xFF9C27B0),
    surface = Color(0xFFFFFFFF),
    background = Color(0xFFF5F5F5),
    error = Color(0xFFB00020)
)