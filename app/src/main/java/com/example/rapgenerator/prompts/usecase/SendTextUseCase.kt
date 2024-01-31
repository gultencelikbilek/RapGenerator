package com.example.rapgenerator.prompts.usecase

import com.example.rapgenerator.di.repository.PromptsRepositoryImpl
import com.example.rapgenerator.model.ChatOpenAi
import com.example.rapgenerator.model.ChatcptRequestBody
import com.example.rapgenerator.model.RapChatCptModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class SendTextUseCase @Inject constructor(private val promptsRepositoryImpl: PromptsRepositoryImpl){
    operator fun invoke(text: String): Response<RapChatCptModel> = promptsRepositoryImpl.sendText(text = text)
}