package com.example.rapgenerator.prompts.usecase

import com.example.rapgenerator.di.repository.PromptsRepositoryImpl
import com.example.rapgenerator.model.ChatGptRequest
import com.example.rapgenerator.model.ChatGptRapResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class SendPromptUseCase @Inject constructor(private val promptsRepositoryImpl: PromptsRepositoryImpl){
    operator suspend fun invoke(prompt: ChatGptRequest): Flow<Response<ChatGptRapResponse>> = promptsRepositoryImpl.sendPrompt(prompt = prompt)
}