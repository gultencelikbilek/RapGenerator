package com.example.rapgenerator.domain.model.chatgpt.chat

import com.google.gson.annotations.SerializedName

data class ChatGptRequestNew(
    @SerializedName("max_tokens")
    val maxTokens: Int?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("prompt")
    val prompt: String?,
    @SerializedName("temperature")
    val temperature: Double?
)