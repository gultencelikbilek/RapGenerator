package com.example.rapgenerator.domain.model.rapper.rapper_url

import com.google.gson.annotations.SerializedName

data class RapperResponseUrlItem(
    @SerializedName("transcription")
    val transcription: String?,
    @SerializedName("url")
    val url: String?
)