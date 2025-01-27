import java.net.URI

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = java.net.URI("https://dl.bintray.com/jetbrains/markdown")
        }
    }
}

plugins {
    id("com.android.application") version "8.1.4" apply false
    id("com.android.library") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jmailen.kotlinter") version "3.13.0" apply false
}

subprojects {
}
//
tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
