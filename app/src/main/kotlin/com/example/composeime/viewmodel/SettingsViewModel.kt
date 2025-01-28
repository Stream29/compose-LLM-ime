package com.example.composeime.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

@Stable
class SettingsViewModel : ViewModel() {
    val tryHereTextFieldValue = mutableStateOf(TextFieldValue("Try here"))
}