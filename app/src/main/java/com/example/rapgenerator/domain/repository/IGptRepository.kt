package com.example.rapgenerator.domain.repository

import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.model.chat.ChatGptRapResponse
import com.example.rapgenerator.domain.model.chat.ChatGptRequestNew
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IGptRepository {
    suspend fun postPrompt(prompt : ChatGptRequestNew) :Response<ChatGptRapResponse>
}