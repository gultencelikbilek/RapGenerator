package com.example.rapgenerator.domain.model.music

data class MusicRequest(
    val backing_track: String,
    val lyrics: List<List<String>>,
    val voicemodel_uuid: String
)