package com.example.rapgenerator.domain.model.chat

import com.google.gson.annotations.SerializedName

data class ChatGptRapResponse(
    @SerializedName("choices")
    val choices: List<Choice?>?,
    @SerializedName("created")
    val created: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("object")
    val objectX: String?,
    @SerializedName("system_fingerprint")
    val systemFingerprint: Any?,
    @SerializedName("usage")
    val usage: Usage?
)