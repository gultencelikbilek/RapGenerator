package com.example.rapgenerator.domain

import android.util.Log

 fun handleError(error: NetworkError) {
    when (error) {
        is NetworkError.ApiError -> {
            Log.d("onError:GeneratingSongViewModel:", error.message)
            // Burada kullanıcıya uygun bir geri bildirim gösterebilirsiniz
        }
        is NetworkError.UnknownError -> {
            Log.d("onError:GeneratingSongViewModel:", "Unknown error occurred: ${error.message}")
            // Burada kullanıcıya uygun bir geri bildirim gösterebilirsiniz
        }
    }
}