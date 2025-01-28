package com.example.composeime.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeime.IMEService
import com.example.composeime.model.AlphabetKey
import com.example.composeime.view.ComposeIMETheme
import com.example.composeime.view.component.KeyboardKey
import com.example.composeime.viewmodel.KeyBoardViewModel

@Preview
@Composable
fun KeyboardScreen(
    viewModel: KeyBoardViewModel = KeyBoardViewModel(IMEService())
) = with(viewModel) {
    ComposeIMETheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .fillMaxWidth()
                .padding(top = 35.dp)
        ) {
            val keyWidth = LocalConfiguration.current.screenWidthDp.dp / 10
            keyRows.forEach { row ->
                Row(
                    modifier = Modifier
                        .requiredHeight(56.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    row.forEach { key ->
                        KeyboardKey(key = key, size = keyWidth)
                    }
                }
            }
        }
    }
}

