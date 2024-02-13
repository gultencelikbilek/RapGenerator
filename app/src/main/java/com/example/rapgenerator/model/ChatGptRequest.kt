package com.example.rapgenerator.model

data class ChatGptRequest(
    val model: String = "gpt-3.5-turbo-instruct",
    val messages:List<Message>,
    val temperature: Int = 1,
    val max_tokens:Int=250,
)