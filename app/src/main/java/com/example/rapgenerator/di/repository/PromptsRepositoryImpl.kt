package com.example.rapgenerator.di.repository

import com.example.rapgenerator.api.ApiService
import com.example.rapgenerator.model.ChatcptRequestBody
import com.example.rapgenerator.model.RapChatCptModel
import com.example.rapgenerator.prompts.repository.IPromptRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class PromptsRepositoryImpl @Inject constructor(private val apiService: ApiService) : IPromptRepository{
    override suspend fun sendText(text: ChatcptRequestBody): Flow<Response<RapChatCptModel>> {

        return apiService.sendText(text)
    }
}