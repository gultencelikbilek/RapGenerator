package com.example.rapgenerator.model

data class ChatOpenAi(
    val frequency_penalty: Int,
    val max_tokens: Int,
    val messages: List<Message>,
    val model: String,
    val n: Int,
    val presence_penalty: Int,
    val stream: Boolean,
    val temperature: Int,
    val top_p: Int
)