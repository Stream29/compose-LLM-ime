package com.example.composeime.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composeime.IMEService
import com.example.composeime.model.*

@Stable
@Suppress("all")
class KeyBoardViewModel(val imeService: IMEService) : ViewModel() {
    var isShift by mutableStateOf(false)
    val keyRows: List<List<Key>> = listOf(
        AlphabetKeyList("qwertyuiop"),
        AlphabetKeyList("asdfghjkl"),
        ShiftKey() + AlphabetKeyList("zxcvbnm") + BackspaceKey()
    )

    fun onInput(content: String) {
        imeService.currentInputConnection.commitText(content, content.length)
    }
}