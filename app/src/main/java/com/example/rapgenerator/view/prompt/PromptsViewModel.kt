package com.example.rapgenerator.view.prompt

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.di.AppModule
import com.example.rapgenerator.domain.model.chatgpt.chat.ChatGptRapResponse
import com.example.rapgenerator.domain.model.chatgpt.chat.ChatGptRequestNew
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromptsViewModel @Inject constructor() : ViewModel() {

    val promptSendText: MutableLiveData<ChatGptRapResponse?> = MutableLiveData()

    fun sendPromptToChatGPT(prompt: ChatGptRequestNew) {
        viewModelScope.launch {
            try{
                val response = AppModule.provideRetrofit().sendPrompt(prompt)
                if(response.isSuccessful){
                    promptSendText.postValue(response.body())
                }
            }catch (e:Exception){
                Log.e("Error Prompt", e.localizedMessage)
            }
        }
    }
}