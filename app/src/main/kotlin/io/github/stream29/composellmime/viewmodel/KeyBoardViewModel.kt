package io.github.stream29.composellmime.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.langchain4j.model.dashscope.QwenChatModel.builder
import io.github.stream29.composellmime.Global
import io.github.stream29.composellmime.IMEService
import io.github.stream29.composellmime.completion.CompletionProvider
import io.github.stream29.composellmime.completion.Context
import io.github.stream29.composellmime.model.*
import io.github.stream29.langchain4kt.api.langchain4j.Langchain4jChatApiProvider
import io.github.stream29.langchain4kt.utils.Plugins
import io.github.stream29.langchain4kt.utils.install
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Stable
@Suppress("all")
class KeyBoardViewModel(val imeService: IMEService) : ViewModel() {
    val keyEventChannel = Channel<Unit>()
    val keyEventFlow = keyEventChannel.receiveAsFlow()
    var inputBuffer by mutableStateOf<String>("")
    var candidate by mutableStateOf<String>("")
    var isShift by mutableStateOf(false)
    val completionProvider = CompletionProvider(
        Langchain4jChatApiProvider(
            builder()
                .apiKey(Global.configs.apiKey)
                .modelName("qwen-max")
                .build()
        ).install (Plugins.logging())
    )

    val keyRows: List<List<Key>> = listOf(
        AlphabetKeyList("qwertyuiop"),
        AlphabetKeyList("asdfghjkl"),
        ShiftKey() + AlphabetKeyList("zxcvbnm") + BackspaceKey(),
        LanguageKey() + SpaceKey() + EnterKey()
    )

    fun onBackspace() {
        if(inputBuffer.isNotEmpty()) {
            inputBuffer = inputBuffer.dropLast(1)
            return
        }
        viewModelScope.launch {
            imeService.currentInputConnection.deleteSurroundingText(1, 0)
            onInput("")
        }
    }

    fun onInput(content: String) {
        viewModelScope.launch {
            if(content.isBlank() && inputBuffer.isBlank())
                commitText(content)
            else
                inputBuffer = inputBuffer + content
            keyEventChannel.send(Unit)
        }
    }

    fun commitAndClear() {
        viewModelScope.launch {
            commitText(candidate)
            candidate = ""
            inputBuffer = ""
        }
    }

    fun commitText(text: String) {
        imeService.currentInputConnection.commitText(text, text.length)
    }

    val textBeforeCursor: String
        get() = imeService.currentInputConnection.getTextBeforeCursor(1000, 0)?.toString() ?: ""

    init {
        viewModelScope.launch(Dispatchers.IO) {
            keyEventFlow.conflate().collect {
                candidate = "generating..."
                candidate = completionProvider.singleCompletion(Context(textBeforeCursor, inputBuffer))
            }
        }
    }
}
