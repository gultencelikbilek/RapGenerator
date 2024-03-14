package com.example.rapgenerator.domain.model.music

data class Line(
    val end: Int,
    val start: Int,
    val text: String,
    val words: List<Word>
)