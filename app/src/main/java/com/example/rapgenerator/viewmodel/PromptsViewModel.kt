package com.example.rapgenerator.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.model.ChatcptRequestBody
import com.example.rapgenerator.model.RapChatCptModel
import com.example.rapgenerator.prompts.usecase.SendTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromptsViewModel @Inject constructor(
    private val sendTextUseCase: SendTextUseCase
) : ViewModel() {

    private val _promptsSendText = MutableStateFlow<RapChatCptModel?>(null)
    val promptSendText: StateFlow<RapChatCptModel?> = _promptsSendText

    fun sendTextToChatGPT(text: ChatcptRequestBody) {
        viewModelScope.launch {
            try {
                sendTextUseCase.invoke(text).collect { response ->
                    if (response.isSuccessful) {
                        _promptsSendText.value = response.body()
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
