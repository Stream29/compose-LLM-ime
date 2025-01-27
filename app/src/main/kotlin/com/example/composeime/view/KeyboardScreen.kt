package com.example.composeime.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeime.view.component.FixedHeightBox
import com.example.composeime.view.component.KeyboardKey

@Composable
fun KeyboardScreen() {
    val keysMatrix = arrayOf(
        arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
        arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
        arrayOf("Z", "X", "C", "V", "B", "N", "M")
    )
    Column(
        modifier = Modifier.background(Color(0xFF9575CD)).fillMaxWidth()
    ) {
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

