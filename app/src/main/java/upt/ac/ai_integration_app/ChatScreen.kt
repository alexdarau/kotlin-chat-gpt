import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun ChatScreen(
    messages: List<Message>,
    onSendMessage: (String) -> Unit
) {
    var userInput by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(messages) { message ->
                ChatBubble(message)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (userInput.isNotBlank()) {
                    onSendMessage(userInput)
                    userInput = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}

@Composable
fun ChatBubble(message: Message) {
    val bubbleColor = if (message.role == "user") Color.Blue else Color.Gray
    val textColor = Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)) {
        Text(
            text = message.content,
            color = textColor,
            modifier = Modifier
                .background(bubbleColor, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        )
    }
}
