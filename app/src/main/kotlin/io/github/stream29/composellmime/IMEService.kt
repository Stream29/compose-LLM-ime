package io.github.stream29.composellmime

import android.content.Context
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import io.github.stream29.composellmime.view.screen.KeyboardScreen
import io.github.stream29.composellmime.viewmodel.KeyBoardViewModel

class IMEService : LifecycleInputMethodService(), ViewModelStoreOwner, SavedStateRegistryOwner {

    override fun onCreateInputView(): View {
        val view = ComposeKeyboardView(this)
        window?.window?.decorView?.let { decorView ->
            decorView.setViewTreeLifecycleOwner(this)
            decorView.setViewTreeViewModelStoreOwner(this)
            decorView.setViewTreeSavedStateRegistryOwner(this)
        }
        return view
    }


    override fun onCreate() {
        super.onCreate()
        savedStateRegistryController.performRestore(null)
    }

    override val viewModelStore: ViewModelStore
        get() = store
    override val lifecycle: Lifecycle
        get() = dispatcher.lifecycle


    //ViewModelStore Methods
    private val store = ViewModelStore()

    //SaveStateRegestry Methods

    private val savedStateRegistryController = SavedStateRegistryController.create(this)

    override val savedStateRegistry: SavedStateRegistry get() = savedStateRegistryController.savedStateRegistry
}

class ComposeKeyboardView(context: Context) : AbstractComposeView(context) {
    @Composable
    override fun Content() {
        Global._storage = LocalContext.current.getExternalFilesDir(null)!!
        val imeService = LocalContext.current as IMEService
        val viewModel = remember { KeyBoardViewModel(imeService) }
        KeyboardScreen(viewModel)
    }
}