import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.openai.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ChatGPTApi {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    suspend fun getChatResponse(
        @Header("Authorization") authorization: String, // Add token in the header
        @Body request: ChatRequest): ChatResponse
}

val chatGPTApi = retrofit.create(ChatGPTApi::class.java)
