package com.example.rapgenerator.repository

import com.example.rapgenerator.model.ChatGptRapResponse
import com.example.rapgenerator.model.chat.ChatGptRequestNew
import retrofit2.Response

interface IPromptRepository {

    suspend fun sendPrompt(prompt : ChatGptRequestNew) : Response<ChatGptRapResponse>
}