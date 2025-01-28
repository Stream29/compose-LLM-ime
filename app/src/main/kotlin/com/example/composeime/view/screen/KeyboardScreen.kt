package com.example.composeime.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeime.view.ComposeIMETheme
import com.example.composeime.view.component.FixedHeightBox
import com.example.composeime.view.component.KeyboardKey

@Preview
@Composable
fun KeyboardScreen() = ComposeIMETheme {
    val keysMatrix = arrayOf(
        arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
        arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
        arrayOf("Z", "X", "C", "V", "B", "N", "M")
    )
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .fillMaxWidth()
            .padding(top = 35.dp)
    ) {
        val keyWidth = LocalConfiguration.current.screenWidthDp.dp / 10
        keysMatrix.forEach { row ->
            FixedHeightBox(
                modifier = Modifier.fillMaxWidth(), height = 56.dp
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    row.forEach { key ->
                        KeyboardKey(keyboardKey = key, modifier = Modifier.widthIn(max = keyWidth))
                    }
                }
            }
        }
    }
}

