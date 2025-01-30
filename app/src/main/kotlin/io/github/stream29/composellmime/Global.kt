package io.github.stream29.composellmime

import io.github.stream29.composellmime.model.Configs
import io.github.stream29.composellmime.model.fromFile
import io.github.stream29.composellmime.model.saveTo
import io.github.stream29.streamlin.AutoUpdatePropertyRoot
import java.io.File

object Global {
    val storageProperty = AutoUpdatePropertyRoot<File>()
    var storage by storageProperty
    val configsFileProperty = storageProperty.subproperty { it.resolve("configs.yml") }
    val configsFile by configsFileProperty
    val configsProperty = configsFileProperty.subproperty { Configs.fromFile(it) }
    val configs by configsProperty
    fun saveConfigs(configs: Configs) {
        configs.saveTo(configsFile)
        storage = storage
    }
}