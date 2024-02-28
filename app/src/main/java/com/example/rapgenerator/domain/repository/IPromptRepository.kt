package com.example.rapgenerator.domain.repository

import com.example.rapgenerator.domain.model.beat.BeatResponse
import com.example.rapgenerator.domain.model.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.chat.ChatGptRapResponse
import com.example.rapgenerator.domain.model.chat.ChatGptRequestNew
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import retrofit2.Call
import retrofit2.Response

interface IPromptRepository {

    suspend fun sendPrompt(prompt : ChatGptRequestNew) : Response<ChatGptRapResponse>

    fun getBeat() : Call<BeatResponse>

    fun getBeatUrl(uuid: String) : Call<BeatUrlResponse>?

   // fun getRapper() : Call<RapperResponse>

  //  suspend fun getRapperRes(): Response<RapperResponse>

    suspend fun getRapperFlow() : RapperResponse

    suspend fun getRapperUlr(id:String) : RapperResponseUrlItem
}