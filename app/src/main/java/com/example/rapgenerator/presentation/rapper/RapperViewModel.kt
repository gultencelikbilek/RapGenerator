package com.example.rapgenerator.presentation.rapper

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.data.usecase.GetRapperUrlUseCase
import com.example.rapgenerator.data.usecase.GetRapperUseCase
import com.example.rapgenerator.domain.NetworkResponse
import com.example.rapgenerator.domain.ResponseStatus
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponseItem
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RapperViewModel @Inject constructor(
    val getRapperUseCase: GetRapperUseCase,
    val getRapperUrlUseCase: GetRapperUrlUseCase
) : ViewModel() {

    private val _rapperUrl = MutableStateFlow<RapperResponseUrlItem?>(null)
    val rapperUrl :StateFlow<RapperResponseUrlItem?> = _rapperUrl

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _rapperResponse = MutableStateFlow<RapperResponse?>(null)
    val rapperResponse: StateFlow<RapperResponse?> = _rapperResponse

    fun getRapper() = viewModelScope.launch {
        getRapperUseCase.invoke().collect { response ->
            when (response) {
                is NetworkResponse.Loading -> {
                    _isLoading.value = true
                }
                is NetworkResponse.Success -> {
                    response.data?.let {
                        _rapperResponse.value = it
                        Log.d("Uberduck:Succes", response.message.toString())

                    }
                    _isLoading.value = false
                }
                is NetworkResponse.Error -> {
                    Log.d("Uberduck Exc", response.message ?: "Unknown error")
                }
            }
        }
    }

    fun getRapperUrl(uuid:String) = viewModelScope.launch {
        Log.d("RapperFragment1", "UUID: $uuid")
        getRapperUrlUseCase.invoke(uuid = uuid).collect{response->
            Log.d("RapperFragment2", "UUID: $uuid")
            when(response.responseStatus){
                ResponseStatus.LOADING -> {
                    _isLoading.value = true
                }
                ResponseStatus.SUCCESS -> {
                    response.data.let {
                        _rapperUrl.value = it
                        Log.d("Uberduck:Succes", response.message.toString())
                    }
                }
                ResponseStatus.ERROR -> {
                    Log.d("Uberduck Exc", response.message ?: "Unknown error")
                }
            }
        }
    }



    //fun getRapperUrl(rapperId:String) = viewModelScope.launch {
   //    try {
   //        val rapperResponseUrl = AppModule.providesBeatRetrofit().getRapperUrl(rapperId)
   //        rapperResponseUrl.let {
   //            _rapperUrl.value = it
   //        }
   //    }catch (e: Exception){
   //        Log.d("error:RapperViewModel:",e.message.toString())
   //    }
   //}

   // fun getRapperUrlTwo(rapperId: String) = viewModelScope.launch {
   //     try {
   //         val rapperUrlResponse = AppModule.providesBeatRetrofit().getRapperUrl(rapperId)
   //         if (rapperUrlResponse.isSuccessful) {
   //             val rapperUrlList = rapperUrlResponse.body()
   //             if (!rapperUrlList.isNullOrEmpty()) {
   //                 _rapperUrl.value = rapperUrlList!!
   //             } else {
   //                 Log.d("RapperViewModel", "Rapper URL list is empty")
   //             }
   //         } else {
   //             Log.e("RapperViewModel", "Retrofit error: ${rapperUrlResponse.code()}")
   //         }
   //     } catch (e: Exception) {
   //         Log.e("RapperViewModel", "Retrofit error: ${Log.getStackTraceString(e)}")
   //     }
   // }


    // fun getRapperRes() = viewModelScope.launch {
    //     try {
    //         val rapperResponse  = AppModule.providesBeatRetrofit().getRapperRes()
    //         if (rapperResponse.isSuccessful){
    //             _rapperResponse.value = rapperResponse.body()
    //         }
    //     }catch (e:Exception){
    //         Log.d("error:rapperviewmodel:",e.message.toString())
    //     }
    // }


    // fun getRapper() = viewModelScope.launch {
    //     val rapperResponse = AppModule.providesBeatRetrofit().getRapper()
    //     rapperResponse.enqueue(object : Callback<RapperResponse>{
    //         override fun onResponse(
    //             call: Call<RapperResponse>,
    //             response: Response<RapperResponse>
    //         ) {
    //             _rapperResponse.value = response.body()
    //         }
//
    //         override fun onFailure(call: Call<RapperResponse>, t: Throwable) {
    //             Log.d("onFailure:",t.message.toString())
    //         }
//
    //     })
    // }
}
