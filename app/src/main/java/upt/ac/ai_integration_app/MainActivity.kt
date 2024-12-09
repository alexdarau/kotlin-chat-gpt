package upt.ac.ai_integration_app

import ChatScreen
import ChatViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme


class MainActivity : ComponentActivity() {
    private val viewModel: ChatViewModel by viewModels() // Corrected ViewModel initialization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ChatScreen(
                    messages = viewModel.messages,
                    onSendMessage = { message -> viewModel.sendMessage(message) }
                )
            }
        }
    }
}

