package com.example.rapgenerator.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.model.ChatGptRequest
import com.example.rapgenerator.model.ChatGptRapResponse
import com.example.rapgenerator.prompts.usecase.SendPromptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromptsViewModel @Inject constructor(
    private val sendPromptUseCase: SendPromptUseCase
) : ViewModel() {

    private val _promptsSendText = MutableStateFlow<ChatGptRapResponse?>(null)
    val promptSendText: StateFlow<ChatGptRapResponse?> = _promptsSendText

    fun sendPromptToChatGPT(prompt: ChatGptRequest) {
        viewModelScope.launch {
            try {
                sendPromptUseCase.invoke(prompt).collect { response ->
                    if (response.isSuccessful) {
                        _promptsSendText.value = response.body()
                        Log.d("onSuccesPromptViewModel:", response.body().toString())
                    } else {
                        Log.d("Error:PromptViewModel", response.errorBody().toString())
                    }
                }
            } catch (e: Exception) {
                Log.e("Error:PromptViewModel", "Exception: ${e.message}")
            }
        }
    }
}
