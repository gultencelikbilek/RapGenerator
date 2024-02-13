package com.example.rapgenerator.api

import com.example.rapgenerator.model.ChatGptRequest
import com.example.rapgenerator.model.ChatGptRapResponse
import com.example.rapgenerator.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

 interface ApiService {
     @POST(Constants.END_POINT)
     @Headers("Authorization: Bearer ${Constants.CHAT_API_KEY}")
     suspend fun sendPrompt(@Body text: ChatGptRequest): Flow<Response<ChatGptRapResponse>>
}