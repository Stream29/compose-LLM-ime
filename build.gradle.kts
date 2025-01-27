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

subprojects {
}
//
tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
