package com.example.rapgenerator.di.repository

import com.example.rapgenerator.api.ApiService
import com.example.rapgenerator.model.ChatOpenAi
import com.example.rapgenerator.model.ChatcptRequestBody
import com.example.rapgenerator.model.Message
import com.example.rapgenerator.model.RapChatCptModel
import com.example.rapgenerator.prompts.repository.IPromptRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PromptsRepositoryImpl @Inject constructor(private val apiService: ApiService) : IPromptRepository{
    override fun sendText(text: String): Response<RapChatCptModel> {
        return apiService.sendText(ChatcptRequestBody("gpt-3.5-turbo-instruct",text,1,250))
    }
}