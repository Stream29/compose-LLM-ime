package io.github.stream29.composellmime.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import io.github.stream29.composellmime.Global

@Stable
class SettingsViewModel(
) : ViewModel() {
    val tryHereTextFieldValue = mutableStateOf(TextFieldValue("Try here"))
    val apiKeyTextFieldValue = mutableStateOf(TextFieldValue(Global.configs.apiKey.toString()))
}