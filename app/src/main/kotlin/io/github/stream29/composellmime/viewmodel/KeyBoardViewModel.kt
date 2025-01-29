package io.github.stream29.composellmime.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.stream29.composellmime.Global
import io.github.stream29.composellmime.IMEService
import io.github.stream29.composellmime.model.*
import dev.langchain4j.model.dashscope.QwenChatModel
import io.github.stream29.langchain4kt.api.langchain4j.Langchain4jChatApiProvider
import io.github.stream29.langchain4kt.core.asRespondent
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
    val respondent = Langchain4jChatApiProvider(
        QwenChatModel.builder()
            .apiKey(Global.configs.apiKey)
            .modelName("qwen-turbo")
            .build()
    ).asRespondent("你的回复应当尽量简短，每次只回复一个词")

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
            imeService.currentInputConnection.commitText(candidate, candidate.length)
            inputBuffer = ""
        }
    }

    val textBeforeCursor: String
        get() = imeService.currentInputConnection.getTextBeforeCursor(1000, 0)?.toString() ?: ""

    init {
        viewModelScope.launch(Dispatchers.IO) {
            keyEventFlow.conflate().collect {
                respondent.chat(
                    "上文为：$textBeforeCursor，用户尝试键盘输入：$inputBuffer，请猜测用户输入的拼音对应的汉字或者词语、短语"
                ).let {
                    candidate = it
                }
            }
        }
    }
}
