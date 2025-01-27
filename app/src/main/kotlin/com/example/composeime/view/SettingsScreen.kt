import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.composeime.view.constant.ComposeIMETheme
import splitties.systemservices.inputMethodManager

@Composable
fun SettingsScreen() {
    ComposeIMETheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column {
                Options()
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
    isSystemInDarkTheme()
}

@Composable
fun Options() {
    Column(
        Modifier.padding(16.dp).fillMaxWidth()
    ) {
        val ctx = LocalContext.current
        Text(text = "Compose Keyboard")
        val (text, setValue) = remember { mutableStateOf(TextFieldValue("Try here")) }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { ctx.startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)) }
        ) {
            Text(text = "Enable IME")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { inputMethodManager.showInputMethodPicker() }
        ) {
            Text(text = "Select IME")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = text,
            onValueChange = setValue,
            modifier = Modifier.fillMaxWidth()
        )
    }
}