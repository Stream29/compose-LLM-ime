package io.github.stream29.composellmime.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.stream29.composellmime.IMEService
import io.github.stream29.composellmime.view.ComposeIMETheme
import io.github.stream29.composellmime.view.component.KeyboardKey
import io.github.stream29.composellmime.viewmodel.KeyBoardViewModel

@Preview
@Composable
fun KeyboardScreen(
    viewModel: KeyBoardViewModel = KeyBoardViewModel(IMEService())
) = with(viewModel) {
    ComposeIMETheme {
        Column {
            if (candidate != "")
                Text(
                    candidate,
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.surfaceVariant),
                )
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
                    Button(
                        onClick = { onInput("") }
                    ) {
                        Text(
                            text = "⟲",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.W900
                        )
                    }
                    TextField(
                        value = inputBuffer,
                        modifier = Modifier.weight(1f),
                        onValueChange = {},
                    )
                    Button(
                        onClick = { commitAndClear() }
                    ) {
                        Text(
                            text = "✓",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                        )
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

