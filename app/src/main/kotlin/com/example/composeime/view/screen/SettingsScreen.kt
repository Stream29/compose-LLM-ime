package com.example.composeime.view.screen

import android.content.Intent
import android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeime.view.ComposeIMETheme
import splitties.systemservices.inputMethodManager

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SettingsScreen() = ComposeIMETheme {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compose IME") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val ctx = LocalContext.current
            val (text, setValue) = remember<MutableState<TextFieldValue>> {
                mutableStateOf<TextFieldValue>(
                    TextFieldValue("Try here")
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { ctx.startActivity(Intent(ACTION_INPUT_METHOD_SETTINGS)) }
            ) {
                Text(text = "Enable IME")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { inputMethodManager.showInputMethodPicker() }
            ) {
                Text(text = "Select IME")
            }
            TextField(
                value = text,
                onValueChange = setValue,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}