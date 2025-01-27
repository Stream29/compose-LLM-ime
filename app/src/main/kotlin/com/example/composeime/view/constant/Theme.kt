package com.example.composeime.view.constant

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ComposeIMETheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colorScheme = if (darkTheme) {
        dynamicLightColorScheme(LocalContext.current)
    } else {
        dynamicDarkColorScheme(LocalContext.current)
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )

}
