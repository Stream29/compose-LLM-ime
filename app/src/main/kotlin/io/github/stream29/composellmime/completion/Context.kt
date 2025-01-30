package io.github.stream29.composellmime.completion

data class Context(
    val prev: String,
    val inputBuffer: String
)
