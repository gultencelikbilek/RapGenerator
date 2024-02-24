package com.example.rapgenerator.di.repository

import com.example.rapgenerator.api.ApiService
import com.example.rapgenerator.domain.model.beat.BeatResponse
import com.example.rapgenerator.domain.model.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.chat.ChatGptRapResponse
import com.example.rapgenerator.domain.model.chat.ChatGptRequestNew
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.repository.IPromptRepository
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class PromptsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    IPromptRepository {
    override suspend fun sendPrompt(prompt: ChatGptRequestNew): Response<ChatGptRapResponse> {
        return apiService.sendPrompt(prompt)
    }

    override fun getBeat(): Call<BeatResponse> {
        return apiService.getBeat()
    }

    override fun getBeatUrl(uuid: String): Call<BeatUrlResponse>? {
        return apiService.getBeatUrl(uuid)
    }

    override fun getRapper(): RapperResponse {
        return apiService.getRapper()
    }
}
