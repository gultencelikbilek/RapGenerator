package com.example.rapgenerator.di.repository

import com.example.rapgenerator.api.ApiService
import com.example.rapgenerator.data.beat.BackingTrack
import com.example.rapgenerator.data.beat.BeatResponse
import com.example.rapgenerator.data.chatgpt.ChatGptRapResponse
import com.example.rapgenerator.data.chatgpt.ChatGptRequestNew
import com.example.rapgenerator.data.repository.IPromptRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PromptsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    IPromptRepository {
    override suspend fun sendPrompt(prompt: ChatGptRequestNew): Response<ChatGptRapResponse> {
        return apiService.sendPrompt(prompt)
    }

    override fun getBeat(): Response<BackingTrack>{
        return apiService.getBeat()
    }
}
