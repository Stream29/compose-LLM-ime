package com.example.composeime

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import com.example.composeime.view.screen.KeyboardScreen

class ComposeKeyboardView(context: Context) : AbstractComposeView(context) {
    @Composable
    override fun Content() {
        KeyboardScreen()
    }
}
