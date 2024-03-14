package com.example.rapgenerator.domain

sealed class NetworkResponse<out T> (val data : T?, val message : String?,val responseStatus : ResponseStatus){
    data class Success<out T>(val _data : T) : NetworkResponse<T>(
        data = _data,
        message = null,
        responseStatus = ResponseStatus.SUCCESS
    )

    data class Error(val _message: String?) : NetworkResponse<Nothing>(
        data = null,
        message = _message,
        responseStatus = ResponseStatus.ERROR
    )

     object Loading:NetworkResponse<Nothing>(
        data = null,
        message = null,
        responseStatus = ResponseStatus.LOADING
    )
}

enum class ResponseStatus{
    LOADING,
    SUCCESS,
    ERROR
}