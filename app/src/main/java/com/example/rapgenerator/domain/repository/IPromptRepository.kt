package com.example.rapgenerator.domain.repository

import com.example.rapgenerator.domain.model.chatgpt.beat.BeatResponse
import com.example.rapgenerator.domain.model.chatgpt.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.chatgpt.chat.ChatGptRapResponse
import com.example.rapgenerator.domain.model.chatgpt.chat.ChatGptRequestNew
import retrofit2.Call
import retrofit2.Response

interface IPromptRepository {

    suspend fun sendPrompt(prompt : ChatGptRequestNew) : Response<ChatGptRapResponse>

    fun getBeat() : Call<BeatResponse>

    fun getBeatUrl(uuid: String) : Call<BeatUrlResponse>?
}