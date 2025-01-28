package com.example.composeime.model

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewModelScope
import com.example.composeime.viewmodel.KeyBoardViewModel
import kotlinx.coroutines.launch

@Stable
data class Key(
    val keyBoardViewModel: KeyBoardViewModel,
    val sizeStrategy: (baseWidth: Dp) -> Modifier,
    val onPress: Key.() -> Unit,
    val onRelease: Key.() -> Unit,
    val onShift: Key.(Boolean) -> Unit,
    var inputContent: String,
) {
    val interactionSource = MutableInteractionSource()
    var shownText by mutableStateOf<String>("A")
    var isPressed by mutableStateOf(false)

    init {
        keyBoardViewModel.viewModelScope.launch {
            interactionSource.interactions.collect {
                when (it) {
                    is PressInteraction.Press -> onPress()
                    is PressInteraction.Release -> onRelease()
                }
            }
        }
    }
}

@Suppress("FunctionName")
fun KeyBoardViewModel.AlphabetKey(name: String) =
    Key(
        keyBoardViewModel = this,
        sizeStrategy = { Modifier.requiredWidth(it) },
        onPress = { isPressed = true },
        onRelease = { isPressed = false;onInput(inputContent) },
        onShift = { isShift ->
            if (isShift) {
                shownText = shownText.uppercase()
                inputContent = inputContent.uppercase()
            } else {
                shownText = shownText.lowercase()
                inputContent = inputContent.lowercase()
            }
        },
        inputContent = name,
    ).apply {
        shownText = name
    }

@Suppress("FunctionName")
fun KeyBoardViewModel.AlphabetKeyList(row: String) =
    row.map { AlphabetKey(it.toString()) }