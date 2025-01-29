package com.example.composeime.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeime.IMEService
import com.example.composeime.view.ComposeIMETheme
import com.example.composeime.view.component.KeyboardKey
import com.example.composeime.viewmodel.KeyBoardViewModel

@Preview
@Composable
fun KeyboardScreen(
    viewModel: KeyBoardViewModel = KeyBoardViewModel(IMEService())
) = with(viewModel) {
    ComposeIMETheme {
        Column {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    TextField(
                        value = inputBuffer,
                        onValueChange = {},
                        readOnly = true,
                    )
                    Button(
                        onClick = { commit() }
                    ) {
                        Text("Commit")
                    }
                }
                val keyWidth = LocalConfiguration.current.screenWidthDp.dp / 10
                keyRows.forEach { row ->
                    Row(
                        modifier = Modifier
                            .weight(1f)
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
}

