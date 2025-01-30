package io.github.stream29.composellmime.model

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewModelScope
import io.github.stream29.composellmime.LongPressConfig
import io.github.stream29.composellmime.viewmodel.KeyBoardViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.systemservices.inputMethodManager

class Key(
    val keyBoardViewModel: KeyBoardViewModel,
    val sizeStrategy: RowScope.(baseWidth: Dp) -> Modifier,
    val onPress: Key.() -> Unit = {},
    val onLongPress: Key.() -> Unit = {},
    val onRelease: Key.() -> Unit = {},
    val onShift: Key.(Boolean) -> Unit = {},
    shownText: String,
) {
    val interactionSource = MutableInteractionSource()
    var shownText by mutableStateOf<String>(shownText)
    var isPressed by mutableStateOf(false)

    init {
        keyBoardViewModel.viewModelScope.launch {
            var currentJob: Job? = null
            interactionSource.interactions.collect {
                if (it is PressInteraction.Press) {
                    onPress()
                    currentJob = launch {
                        delay(LongPressConfig.wait)
                        var count: Long = LongPressConfig.frac
                        while (true) {
                            onLongPress()
                            delay(50 * LongPressConfig.frac / count + 1)
                            count++
                        }
                    }
                    return@collect
                } else {
                    currentJob?.cancel()
                }
                if (it is PressInteraction.Release) {
                    onRelease()
                }
            }
        }
    }
}

@Suppress("FunctionName")
fun KeyBoardViewModel.SpaceKey() =
    AlphabetKey(" ", sizeStrategy = { Modifier.width(it * 7) })

@Suppress("FunctionName")
fun KeyBoardViewModel.EnterKey() =
    AlphabetKey("\n", "⏎", sizeStrategy = { Modifier.weight(1f) })

@Suppress("FunctionName")
fun KeyBoardViewModel.LanguageKey() =
    Key(
        keyBoardViewModel = this,
        sizeStrategy = { Modifier.weight(1f) },
        onPress = { inputMethodManager.showInputMethodPicker() },
        shownText = "\uD83C\uDF0D"
    )

@Suppress("FunctionName")
fun KeyBoardViewModel.ShiftKey() =
    Key(
        keyBoardViewModel = this,
        sizeStrategy = { Modifier.weight(1f) },
        onRelease = {
            isShift = !isShift
            viewModelScope.launch {
                keyRows.asSequence().map { it.asSequence() }.flatten().forEach { it.onShift(it, isShift) }
            }
        },
        shownText = "↑"
    )

@Suppress("FunctionName")
fun KeyBoardViewModel.BackspaceKey() =
    Key(
        keyBoardViewModel = this,
        sizeStrategy = { Modifier.weight(1f) },
        onPress = { onBackspace() },
        onLongPress = { onBackspace() },
        shownText = "←",
    )

@Suppress("FunctionName")
fun KeyBoardViewModel.AlphabetKey(
    text: String,
    shownText: String = text,
    sizeStrategy: RowScope.(Dp) -> Modifier = { Modifier.width(it) }
) = Key(
    keyBoardViewModel = this,
    sizeStrategy = sizeStrategy,
    onPress = { isPressed = true },
    onLongPress = { onInput(text) },
    onRelease = { isPressed = false;onInput(text) },
    onShift = { isShift ->
        this.shownText = if (isShift) {
            shownText.uppercase()
        } else {
            shownText.lowercase()
        }
    },
    shownText = shownText
)

@Suppress("FunctionName")
fun KeyBoardViewModel.AlphabetKeyList(row: String) =
    row.map { AlphabetKey(it.toString()) }

operator fun Key.plus(list: List<Key>): List<Key> = listOf(this) + list

operator fun Key.plus(other: Key): List<Key> = listOf(this, other)