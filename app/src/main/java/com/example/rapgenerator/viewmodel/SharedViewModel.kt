package com.example.rapgenerator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel  @Inject constructor():ViewModel(){

    private var _chatcptRequestBody =MutableLiveData<String>()
    var chatcptRequestBody = _chatcptRequestBody


    fun chatRequestBody(chatcptRequestBody: String) {
        _chatcptRequestBody.value = chatcptRequestBody
    }

}