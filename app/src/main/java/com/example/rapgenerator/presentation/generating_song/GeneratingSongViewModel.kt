package com.example.rapgenerator.presentation.generating_song

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.domain.model.music.MusicRequest
import com.example.rapgenerator.domain.model.music.MusicResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.Credentials
import javax.inject.Inject

@HiltViewModel
class GeneratingSongViewModel @Inject constructor() : ViewModel() {


     private val _postFreestyle = MutableLiveData<MusicResponse>()
     val postFreestyle :LiveData<MusicResponse> = _postFreestyle

   // fun postFreestyle(musicRequestBody: MusicRequest) = viewModelScope.launch {
   //     val credentials = Credentials.basic("pub_dpjmctzvtkjqliiavy", "pk_050df840-74e9-4cda-98a4-3da5e4fd4d0d")
   //     val response = AppModule.providesBeatRetrofit()
   //         .postFreestyle(
   //             musicRequestBody
   //         )
   //     if (response.isSuccessful) {
   //         _postFreestyle.value = response.body()
   //         Log.d("succes:generatingViewModel:", response.body().toString())
   //     } else {
   //         val errorBody = response.errorBody()?.string()
   //         Log.d("error:generatingViewModel:", errorBody ?: "Error body is null")
   //     }
   // }
}