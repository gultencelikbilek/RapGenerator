package com.example.rapgenerator.data.repository

import com.example.rapgenerator.data.beat.BackingTrack
import com.example.rapgenerator.data.beat.BeatResponse
import com.example.rapgenerator.data.chatgpt.ChatGptRapResponse
import com.example.rapgenerator.data.chatgpt.ChatGptRequestNew
import retrofit2.Call
import retrofit2.Response

interface IPromptRepository {

    suspend fun sendPrompt(prompt : ChatGptRequestNew) : Response<ChatGptRapResponse>

    fun getBeat() : Call<BeatResponse>
}