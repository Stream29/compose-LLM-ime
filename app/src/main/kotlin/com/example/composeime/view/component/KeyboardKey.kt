package com.example.composeime.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.composeime.IMEService

@Composable
fun KeyboardKey(
    keyboardKey: String,
    modifier: Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed = interactionSource.collectIsPressedAsState()
    val ctx = LocalContext.current
    Box(modifier = modifier.fillMaxHeight(), contentAlignment = Alignment.Companion.BottomCenter) {
        Text(
            text = keyboardKey,
            modifier = Modifier.Companion.fillMaxWidth()
                .padding(2.dp)
                .border(1.dp, Color.Companion.Black)
                .clickable(interactionSource = interactionSource, indication = null) {
                    (ctx as IMEService).currentInputConnection.commitText(
                        keyboardKey,
                        keyboardKey
                            .length
                    )
                }
                .background(Color.Companion.White)
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )

        )
        if (pressed.value) {
            Text(
                text = keyboardKey,
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .border(1.dp, Color.Companion.Black)
                    .background(Color.Companion.White)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 48.dp
                    )
            )
        }
    }
}