package io.github.stream29.composellmime.completion

import io.github.stream29.langchain4kt.core.ChatApiProvider
import io.github.stream29.langchain4kt.core.asRespondent

class CompletionProvider(
    val chatApiProvider: ChatApiProvider<*>
) {
    val singleRespondent = chatApiProvider.asRespondent("你的回复应当尽量简短，每次只回复一个词")
    suspend fun singleCompletion(context: Context): String {
        with(context) {
            return singleRespondent.chat(
                "上文为：${prev}，用户在键盘按下按键：$inputBuffer，请猜测用户想要输入的内容"
            )
        }
    }

    suspend fun multiCompletion(context: Context): List<String> {
        TODO()
    }

    suspend fun afterSingleCompletion(prev: String): String {
        TODO()
    }

    suspend fun afterMultiCompletion(prev: String): List<String> {
        TODO()
    }
}