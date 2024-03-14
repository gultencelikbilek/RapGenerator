package com.example.rapgenerator.domain.model.music

data class MusicResponse(
    val bpm: Double,
    val lines: List<Line>,
    val mix_url: String,
    val render_uuid: String,
    val render_video_response: Any,
    val timestamps: Any,
    val title: String,
    val vocals_url: String
)