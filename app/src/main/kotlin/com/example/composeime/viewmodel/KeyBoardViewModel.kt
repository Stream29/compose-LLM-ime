package com.example.composeime.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeime.IMEService
import com.example.composeime.model.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Stable
@Suppress("all")
class KeyBoardViewModel(val imeService: IMEService) : ViewModel() {
    val keyEventChannel = Channel<Unit>()
    val keyEventFlow = keyEventChannel.receiveAsFlow()
    var inputBuffer by mutableStateOf<String>("")
    var isShift by mutableStateOf(false)
    val keyRows: List<List<Key>> = listOf(
        AlphabetKeyList("qwertyuiop"),
        AlphabetKeyList("asdfghjkl"),
        ShiftKey() + AlphabetKeyList("zxcvbnm") + BackspaceKey(),
        LanguageKey() + SpaceKey() + EnterKey()
    )

    fun onInput(content: String) {
        viewModelScope.launch {
            inputBuffer = inputBuffer + content
            keyEventChannel.send(Unit)
        }
    }

    fun commit() {
        viewModelScope.launch {
            imeService.currentInputConnection.commitText(inputBuffer, inputBuffer.length)
            inputBuffer = ""
        }
    }

    val textBeforeCursor: String
        get() = imeService.currentInputConnection.getTextBeforeCursor(1000, 0)?.toString() ?: ""

}