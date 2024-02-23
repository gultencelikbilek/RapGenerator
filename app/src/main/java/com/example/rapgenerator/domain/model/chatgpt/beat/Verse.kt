package com.example.rapgenerator.domain.model.chatgpt.beat

data class Verse(
    val label: String,
    val length_in_measures: Int,
    val start: Double
)