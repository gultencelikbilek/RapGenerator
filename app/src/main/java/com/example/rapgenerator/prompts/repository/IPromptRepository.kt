package com.example.rapgenerator.prompts.repository

import com.example.rapgenerator.model.ChatcptRequestBody
import com.example.rapgenerator.model.RapChatCptModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

interface IPromptRepository {

   suspend fun sendText(text : ChatcptRequestBody) : Flow<Response<RapChatCptModel>>
}