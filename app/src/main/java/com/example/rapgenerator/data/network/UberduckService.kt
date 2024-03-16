package com.example.rapgenerator.data.network

import com.example.rapgenerator.domain.model.beat.BeatResponse
import com.example.rapgenerator.domain.model.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.music.MusicRequest
import com.example.rapgenerator.domain.model.music.MusicResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponseItem
import com.example.rapgenerator.domain.model.rapper.RapperUrlResponse
import com.example.rapgenerator.domain.model.rapper.RapperUrlResponseItem
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import com.example.rapgenerator.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface UberduckService {

    @GET(Constants.END_POINT_BEAT)
    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
    fun getBeat(): Call<BeatResponse>

    @GET("${Constants.END_POINT_BEAT}/{uuid}")
    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
    fun getBeatUrl(
        @Path("uuid") uuid: String
    ): Call<BeatUrlResponse>

    @GET(Constants.END_POINT_RAPPER)
    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
     fun getRapper(): Call<RapperResponse>

    @GET("${Constants.END_POINT_RAPPER}/{voicemodel_uuid}/samples")
    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
     fun getRapperUrl(
        @Path("voicemodel_uuid") uuid: String
    ): Call<RapperUrlResponse>


     @POST("${Constants.END_POINT_MUSIC}")
     @Headers("Authorization: Basic ${Constants.API_KEY_MUSIC}" )
      fun postFreestyle(@Body musicRequest: MusicRequest) : Call<MusicResponse>
}