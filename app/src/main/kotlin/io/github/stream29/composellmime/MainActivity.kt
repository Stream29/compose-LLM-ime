package io.github.stream29.composellmime

import io.github.stream29.composellmime.view.screen.SettingsScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import io.github.stream29.composellmime.viewmodel.SettingsViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Global._storage = LocalContext.current.getExternalFilesDir(null)!!
            val viewModel = remember { SettingsViewModel() }
            SettingsScreen(viewModel)
        }
    }
}