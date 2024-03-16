package com.example.rapgenerator.presentation.generating_song

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.data.usecase.PostFreestleUseCase
import com.example.rapgenerator.domain.NetworkError
import com.example.rapgenerator.domain.ResponseStatus
import com.example.rapgenerator.domain.handleError
import com.example.rapgenerator.domain.model.music.MusicRequest
import com.example.rapgenerator.domain.model.music.MusicResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratingSongViewModel @Inject constructor(
     val postFreestyleUseCase: PostFreestleUseCase
) : ViewModel() {

     private val _postFreestyle = MutableStateFlow<MusicResponse?>(null)
     val postFreestyle :StateFlow<MusicResponse?> = _postFreestyle

     private val _isLoading = MutableStateFlow<Boolean>(false)
     val isLoading :StateFlow<Boolean> = _isLoading

     fun postFreestyle(musicRequestBody: MusicRequest) = viewModelScope.launch {
          postFreestyleUseCase.invoke(musicRequestBody).collect { response ->
               when (response.responseStatus) {
                    ResponseStatus.LOADING -> {
                         _isLoading.value = true
                    }
                    ResponseStatus.SUCCESS -> {
                         response.data?.let { data ->
                              _postFreestyle.value = data
                              Log.d("MusicResponse:Success", response.message ?: "Success")
                         }
                    }
                    ResponseStatus.ERROR -> {
                         response.message?.let { errorMessage ->
                              val error = NetworkError.ApiError(errorMessage)
                              handleError(error)
                         }
                    }
               }
               _isLoading.value = false // Hata durumunda bile isLoading değerini false olarak ayarlayın
          }
     }
}