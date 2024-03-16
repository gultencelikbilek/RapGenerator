package com.example.rapgenerator.domain.model.rapper

data class RapperResponseItem(
    val category: String,
    val display_name: String,
    val is_private: Boolean,
    val language: String,
    val name: String,
    val voicemodel_uuid:String
)