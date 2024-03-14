package com.example.rapgenerator.data.network.repoImpl

import com.example.rapgenerator.data.network.GptService
import com.example.rapgenerator.domain.model.chat.ChatGptRapResponse
import com.example.rapgenerator.domain.model.chat.ChatGptRequestNew
import com.example.rapgenerator.domain.repository.IGptRepository
import retrofit2.Response
import javax.inject.Inject

class GptRepositoryImpl @Inject constructor(private val gptService: GptService) : IGptRepository {
    override suspend fun postPrompt(prompt: ChatGptRequestNew): Response<ChatGptRapResponse> {
        return gptService.postPrompt(prompt)
    }

}
