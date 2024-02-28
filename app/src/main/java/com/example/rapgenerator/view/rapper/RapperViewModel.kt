package com.example.rapgenerator.view.rapper

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.di.AppModule
import com.example.rapgenerator.domain.model.rapper.RapperResponse
import com.example.rapgenerator.domain.model.rapper.RapperResponseItem
import com.example.rapgenerator.domain.model.rapper.rapper_url.RapperResponseUrlItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RapperViewModel @Inject constructor() : ViewModel() {

    private val _rapperResponse = MutableLiveData<List<RapperResponseItem>>()
    val rapperResponse: LiveData<List<RapperResponseItem>> = _rapperResponse

    private val _rapperFlow = MutableStateFlow<List<RapperResponseItem?>>(emptyList())
    val rapperFlow: StateFlow<List<RapperResponseItem?>> = _rapperFlow.asStateFlow()


    private val _rapperUrl = MutableStateFlow<RapperResponseUrlItem?>(null)
    val rapperUrl : StateFlow<RapperResponseUrlItem?> = _rapperUrl.asStateFlow()

    fun getRapperFlow() = viewModelScope.launch {
        try {
            val rapperFlow = AppModule.providesBeatRetrofit().getRapperFlow()
            rapperFlow.let {
                _rapperFlow.value = it
            }
        } catch (e: Exception) {
            Log.d("error:rapperviewmodel:", e.message.toString())
        }
    }

    fun getRapperUrl(rapperId:String) = viewModelScope.launch {
        try {
            val rapperResponseUrl = AppModule.providesBeatRetrofit().getRapperUrl(rapperId)
            rapperResponseUrl.let {
                _rapperUrl.value = it
            }
        }catch (e: Exception){
            Log.d("error:RapperViewModel:",e.message.toString())
        }
    }


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
