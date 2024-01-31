package com.example.rapgenerator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rapgenerator.model.RapChatCptModel
import com.example.rapgenerator.prompts.usecase.SendTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PromptsViewModel @Inject constructor(
    private val sendTextUseCase: SendTextUseCase
) : ViewModel() {

    private val _promptsSendText = MutableLiveData<Response<RapChatCptModel>>()
    val promptSendText: LiveData<Response<RapChatCptModel>>
        get() = _promptsSendText

    fun sendTextToChatGPT(text: String) {
        try {
            // Make the API call using Retrofit
            val response = sendTextUseCase.invoke(text)
            _promptsSendText.value = Response.success(response.body())
        } catch (e: Exception) {

        }
    }
}
