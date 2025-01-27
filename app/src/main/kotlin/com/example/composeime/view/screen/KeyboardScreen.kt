package com.example.composeime.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeime.view.ComposeIMETheme
import com.example.composeime.view.component.FixedHeightBox
import com.example.composeime.view.component.KeyboardKey

@Composable
fun KeyboardScreen() = ComposeIMETheme {
    val keysMatrix = arrayOf(
        arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
        arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
        arrayOf("Z", "X", "C", "V", "B", "N", "M")
    )
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer).fillMaxWidth()
    ) {
        Row(Modifier.fillMaxWidth().padding(16.dp)) {}
        keysMatrix.forEach { row ->
            FixedHeightBox(
                modifier = Modifier.fillMaxWidth(), height = 56.dp
            ) {
                Row(Modifier) {
                    row.forEach { key ->
                        KeyboardKey(keyboardKey = key, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

