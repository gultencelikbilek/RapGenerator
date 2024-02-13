package com.example.rapgenerator.prompts.repository

import com.example.rapgenerator.model.ChatGptRequest
import com.example.rapgenerator.model.ChatGptRapResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IPromptRepository {

    suspend fun sendPrompt(prompt : ChatGptRequest) : Flow<Response<ChatGptRapResponse>>
}