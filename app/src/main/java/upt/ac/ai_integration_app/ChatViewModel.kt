import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _messages = mutableStateListOf<Message>()
    val messages: List<Message> get() = _messages

    fun sendMessage(userMessage: String) {
        _messages.add(Message("user", userMessage))
        Log.d("ChatGPT Messages Before Request", "Messages: $_messages")
        viewModelScope.launch {
            try {
                val request = ChatRequest(
                    model = "gpt-3.5-turbo",
                    messages = _messages.toList()
                )
                val token = "Bearer" //TODO: Add a token for Chat GPT
                Log.d("ChatGPT Messages Before Request", "Messages: $request")
                val response = chatGPTApi.getChatResponse(token, request)

                val botMessage = response.choices.firstOrNull()?.message?.content
                if (botMessage != null) {
                    _messages.add(Message("system", botMessage))
                }
            } catch (e: Exception) {
                _messages.add(Message("system", "Failed to fetch response. Please try again. $e"))
            }
        }
    }
}
