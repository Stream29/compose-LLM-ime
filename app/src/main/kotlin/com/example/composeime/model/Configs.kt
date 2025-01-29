package com.example.composeime.model

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class Configs(
    val apiKey: String? = null,
) {
    companion object
}

fun Configs.Companion.fromFile(file: File): Configs =
    runCatching {
        Yaml.default.decodeFromString(serializer(), file.readText())
    }.getOrElse { Configs() }

fun Configs.saveTo(file: File) {
    if(!file.exists()) file.createNewFile()
    file.writeText(Yaml.default.encodeToString(Configs.serializer(), this))
}