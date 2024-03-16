package com.example.rapgenerator.domain.model.music

data class MusicRequest(
    val backing_track: String ,
    val lyrics: List<List<String>>,
    val voicemodel_uuid: String = "c8a916b4-4574-4042-82a1-3cead35331c9"
)