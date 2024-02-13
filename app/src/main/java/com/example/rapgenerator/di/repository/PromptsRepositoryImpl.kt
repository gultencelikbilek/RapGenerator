package com.example.rapgenerator.di.repository

import com.example.rapgenerator.api.ApiService
import com.example.rapgenerator.model.ChatGptRequest
import com.example.rapgenerator.model.ChatGptRapResponse
import com.example.rapgenerator.model.Message
import com.example.rapgenerator.prompts.repository.IPromptRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PromptsRepositoryImpl @Inject constructor(private val apiService: ApiService) : IPromptRepository{
    override suspend fun sendPrompt(prompt: ChatGptRequest): Flow<Response<ChatGptRapResponse>> {
        return apiService.sendPrompt(prompt)
    }
}