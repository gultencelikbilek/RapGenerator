package com.example.rapgenerator.di.repository

import com.example.rapgenerator.api.ApiService
import com.example.rapgenerator.model.ChatGptRapResponse
import com.example.rapgenerator.model.chat.ChatGptRequestNew
import com.example.rapgenerator.repository.IPromptRepository
import retrofit2.Response
import javax.inject.Inject

class PromptsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    IPromptRepository {
    override suspend fun sendPrompt(prompt: ChatGptRequestNew): Response<ChatGptRapResponse> {
        return apiService.sendPrompt(prompt)
    }
}
