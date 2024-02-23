package com.example.rapgenerator.di.repository

import android.util.Log
import com.example.rapgenerator.api.ApiService
import com.example.rapgenerator.domain.model.chatgpt.beat.BeatResponse
import com.example.rapgenerator.domain.model.chatgpt.beat.beat_url.BeatUrlResponse
import com.example.rapgenerator.domain.model.chatgpt.chat.ChatGptRapResponse
import com.example.rapgenerator.domain.model.chatgpt.chat.ChatGptRequestNew
import com.example.rapgenerator.domain.repository.IPromptRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PromptsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    IPromptRepository {
    override suspend fun sendPrompt(prompt: ChatGptRequestNew): Response<ChatGptRapResponse> {
        return apiService.sendPrompt(prompt)
    }

    override fun getBeat(): Call<BeatResponse>{
        return apiService.getBeat()
    }

    override fun getBeatUrl(uuid: String): Call<BeatUrlResponse>? {
        return  apiService.getBeatUrl(uuid)

   //   val responseBeatUrl = apiService.getBeatUrl(uuid)
   //   responseBeatUrl.enqueue(object : Callback<BeatUrlResponse> {
   //       override fun onResponse(
   //           call: Call<BeatUrlResponse>,
   //           response: Response<BeatUrlResponse>
   //       ) {
   //           if (response.isSuccessful){
   //               response.body().let {
   //                   return response.body()?.backing_tracks
   //               }
   //           }
   //       }

   //       override fun onFailure(call: Call<BeatUrlResponse>, t: Throwable) {
   //           Log.d("onFailure:BeatUrlResponse:",t.message.toString())
   //       }

   //   })

   //   return null

    }
}
