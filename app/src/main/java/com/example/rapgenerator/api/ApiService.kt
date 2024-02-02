package com.example.rapgenerator.api

import com.example.rapgenerator.model.ChatcptRequestBody
import com.example.rapgenerator.model.RapChatCptModel
import com.example.rapgenerator.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

 interface ApiService {
     @Headers("Authorization: Bearer ${Constants.CHAT_API_KEY}")
     @POST("chat/completions")
     suspend fun sendText(@Body text: ChatcptRequestBody): Flow<Response<RapChatCptModel>>
}