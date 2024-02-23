package com.example.rapgenerator.domain.model.chatgpt.beat

data class BackingTrack(
    val bpm: Double,
    val bucket: String,
    val is_public: Boolean,
    val name: String,
    val path: String,
    val source: String,
    val uuid: String,
    val verses: List<Verse>
)