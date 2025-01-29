package com.example.composeime.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composeime.IMEService
import com.example.composeime.model.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Stable
@Suppress("all")
class KeyBoardViewModel(val imeService: IMEService) : ViewModel() {
    val keyEventChannel = Channel<Unit>()
    val keyEventFlow = keyEventChannel.receiveAsFlow()
    var isShift by mutableStateOf(false)
    val keyRows: List<List<Key>> = listOf(
        AlphabetKeyList("qwertyuiop"),
        AlphabetKeyList("asdfghjkl"),
        ShiftKey() + AlphabetKeyList("zxcvbnm") + BackspaceKey(),
        LanguageKey() + SpaceKey() + EnterKey()
    )

    fun onInput(content: String) {
        imeService.currentInputConnection.commitText(content, content.length)
    }

    val textBeforeCursor: String
        get() = imeService.currentInputConnection.getTextBeforeCursor(1000, 0)?.toString() ?: ""

    init {

    }
}