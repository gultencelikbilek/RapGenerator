package com.example.rapgenerator.api

import com.example.rapgenerator.domain.model.beat.BeatResponse
import com.example.rapgenerator.domain.model.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.chat.ChatGptRapResponse
import com.example.rapgenerator.domain.model.chat.ChatGptRequestNew
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import com.example.rapgenerator.utils.Constants
import kotlinx.coroutines.flow.Flow
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

    @GET(Constants.END_POINT_RAPPER)
    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
   // fun getRapper(): Call<RapperResponse>
   // suspend fun getRapperRes(): Response<RapperResponse>
    suspend fun getRapperFlow() : RapperResponse

    @GET("${Constants.END_POINT_RAPPER}/{voicemodel_uuid}/samples")
    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
    fun getRapperUrl(
        @Path("voicemodel_uuid") uuid : String
    ) : RapperResponseUrlItem

}