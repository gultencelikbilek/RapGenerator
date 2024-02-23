package com.example.rapgenerator.domain.model.chatgpt.beat.beat_url

import com.google.gson.annotations.SerializedName

data class BeatUrlResponse(
    @SerializedName("backing_track")
    val backingTrack: BackingTrack?
)