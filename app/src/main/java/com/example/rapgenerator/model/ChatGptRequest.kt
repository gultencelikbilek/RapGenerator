package com.example.rapgenerator.model

import com.google.gson.annotations.SerializedName

data class ChatGptRequest(
    @SerializedName("max_tokens")
    val maxTokens: Int?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("prompt")
    val prompt: String?,
    @SerializedName("temperature")
    val temperature: Double?

)