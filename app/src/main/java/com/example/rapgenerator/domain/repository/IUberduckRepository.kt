package com.example.rapgenerator.domain.repository

import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.model.beat.BeatResponse
import com.example.rapgenerator.domain.model.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.music.MusicRequest
import com.example.rapgenerator.domain.model.music.MusicResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponseItem
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

interface IUberduckRepository {
    fun getBeat() : Flow<NetworkResponse<BeatResponse>>

    fun getBeatUrl(uuid: String) : Flow<NetworkResponse<BeatUrlResponse>>

     fun getRapper() :Flow<NetworkResponse<RapperResponse>>

     fun getRapperUlr(id:String) :Flow<NetworkResponse<RapperResponseUrlItem>>

    suspend fun sendRapper(musicRequest : MusicRequest) : Response<MusicResponse>
}