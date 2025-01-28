package com.example.composeime.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel

@Stable
class KeyBoardViewModel: ViewModel() {
    val keysMatrix = arrayOf(
        arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
        arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
        arrayOf("Z", "X", "C", "V", "B", "N", "M")
    )
}