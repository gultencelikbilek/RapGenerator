package com.example.rapgenerator.domain.repository

import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.model.beat.BeatResponse
import com.example.rapgenerator.domain.model.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.music.MusicRequest
import com.example.rapgenerator.domain.model.music.MusicResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.RapperUrlResponse
import com.example.rapgenerator.domain.model.rapper.RapperUrlResponseItem
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import kotlinx.coroutines.flow.Flow

interface IUberduckRepository {
    fun getBeat() : Flow<NetworkResponse<BeatResponse>>

    fun getBeatUrl(uuid: String) : Flow<NetworkResponse<BeatUrlResponse>>

     fun getRapper() :Flow<NetworkResponse<RapperResponse>>

     fun getRapperUrl(id:String) :Flow<NetworkResponse<RapperUrlResponse>>

     fun postFreestyle(musicRequest : MusicRequest) : Flow<NetworkResponse<MusicResponse>>
}