package com.example.rapgenerator.prompts.repository

import com.example.rapgenerator.model.ChatOpenAi
import com.example.rapgenerator.model.ChatcptRequestBody
import com.example.rapgenerator.model.Message
import com.example.rapgenerator.model.RapChatCptModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IPromptRepository {

    fun sendText(text : String) : Response<RapChatCptModel>
}