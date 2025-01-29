package com.example.composeime

import com.example.composeime.model.Configs
import com.example.composeime.model.fromFile
import com.example.composeime.model.saveTo
import java.io.File

object Global {
    @Suppress("ObjectPropertyName")
    var _storage: File = File("")
        set(value) {
            field = value
            reloadConfigs()
        }
    val storage: File
        get() = _storage
    val configsFile: File
        get() = storage.resolve("configs.yml")
    private var _configs: Configs = Configs.fromFile(configsFile)
    val configs: Configs
        get() = _configs

    fun reloadConfigs() {
        _configs = Configs.fromFile(configsFile)
    }

    fun saveConfigs(configs: Configs) {
        _configs = configs
        configs.saveTo(configsFile)
    }
}