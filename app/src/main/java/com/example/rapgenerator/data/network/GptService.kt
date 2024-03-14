package com.example.rapgenerator.data.network

import com.example.rapgenerator.domain.model.chat.ChatGptRapResponse
import com.example.rapgenerator.domain.model.chat.ChatGptRequestNew
import com.example.rapgenerator.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GptService {
    @POST(Constants.END_POINT)
    @Headers("Authorization: Bearer ${Constants.API_KEY_CHAT}")
    suspend fun postPrompt(@Body text: ChatGptRequestNew): Response<ChatGptRapResponse>
}