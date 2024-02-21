package com.example.rapgenerator.api

import com.example.rapgenerator.data.beat.BackingTrack
import com.example.rapgenerator.data.beat.BeatResponse
import com.example.rapgenerator.data.chatgpt.ChatGptRapResponse
import com.example.rapgenerator.data.chatgpt.ChatGptRequestNew
import com.example.rapgenerator.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST(Constants.END_POINT)
    @Headers("Authorization: Bearer ${Constants.CHAT_API_KEY}")
    suspend fun sendPrompt(@Body text: ChatGptRequestNew): Response<ChatGptRapResponse>

    @GET(Constants.BEAT_END_POINT)
    @Headers("Authorization: Basic ${Constants.BEAT_API_KEY}")
    fun getBeat() : Call<BeatResponse>
}