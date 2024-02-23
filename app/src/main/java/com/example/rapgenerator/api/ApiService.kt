package com.example.rapgenerator.api

import com.example.rapgenerator.domain.model.chatgpt.beat.BeatResponse
import com.example.rapgenerator.domain.model.chatgpt.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.chatgpt.chat.ChatGptRapResponse
import com.example.rapgenerator.domain.model.chatgpt.chat.ChatGptRequestNew
import com.example.rapgenerator.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST(Constants.END_POINT)
    @Headers("Authorization: Bearer ${Constants.API_KEY_CHAT}")
    suspend fun sendPrompt(@Body text: ChatGptRequestNew): Response<ChatGptRapResponse>

    @GET(Constants.END_POINT_BEAT)
    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
    fun getBeat(): Call<BeatResponse>

    @GET("${Constants.END_POINT_BEAT_URL}/{uuid}")
    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
    fun getBeatUrl(
        @Path("uuid") uuid: String
    ): Call<BeatUrlResponse>
}