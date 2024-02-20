package com.example.rapgenerator.data.chatgpt

import com.google.gson.annotations.SerializedName

data class Choice(
    @SerializedName("finish_reason")
    val finishReason: String?,
    @SerializedName("index")
    val index: Int?,
    @SerializedName("logprobs")
    val logprobs: Any?,
    //@SerializedName("message")
    var text: String?,
)