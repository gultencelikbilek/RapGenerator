package com.example.rapgenerator.domain

sealed class NetworkError(val message: String) {
    class ApiError(message: String) : NetworkError(message)
    class UnknownError(message: String) : NetworkError(message)
}