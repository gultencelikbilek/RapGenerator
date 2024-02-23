package com.example.rapgenerator.view.beats

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.domain.model.chatgpt.beat.BeatResponse
import com.example.rapgenerator.di.AppModule
import com.example.rapgenerator.domain.model.chatgpt.beat.beat_url.BeatUrlResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SelectBeatViewModel @Inject constructor(): ViewModel() {

    private val _selectBeat = MutableLiveData<List<com.example.rapgenerator.domain.model.chatgpt.beat.BackingTrack>>() // Değişiklik burada
    val selectBeat: LiveData<List<com.example.rapgenerator.domain.model.chatgpt.beat.BackingTrack>> = _selectBeat // Değişiklik burada

    private val _beatUrl = MutableLiveData<com.example.rapgenerator.domain.model.chatgpt.beat.beat_url.BackingTrack>()
    val beatUrl : LiveData<com.example.rapgenerator.domain.model.chatgpt.beat.beat_url.BackingTrack> = _beatUrl

    fun getBeat() = viewModelScope.launch {
        val responseBeat = AppModule.providesBeatRetrofit().getBeat()
        responseBeat.enqueue(object : Callback<BeatResponse>{
            override fun onResponse(call: Call<BeatResponse>, response: Response<BeatResponse>) {
                if (response.isSuccessful){
                    _selectBeat.value = response.body()!!.backing_tracks
                    Log.d("onsucces:SealectBeatViewModel:",response.message()
                        .toString())
                }else{
                    Log.d("onsucces:SealectBeatViewModel:",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<BeatResponse>, t: Throwable) {
                Log.d("onFailure:SelectbeatViewModel",t.message.toString())
            }

        })
    }

    fun getBeatUrl(uuid:String) = viewModelScope.launch {
        val responseBeatUrl = AppModule.providesBeatRetrofit().getBeatUrl(uuid)
        responseBeatUrl.enqueue(object : Callback<BeatUrlResponse>{
            override fun onResponse(
                call: Call<BeatUrlResponse>,
                response: Response<BeatUrlResponse>
            ) {
                if (response.isSuccessful){
                    response.body().let {
                        _beatUrl.value = response.body()!!.backingTrack!!
                    }
                }
            }

            override fun onFailure(call: Call<BeatUrlResponse>, t: Throwable) {
                Log.d("onFailure:BeatUrlResponse:",t.message.toString())
            }

        })
    }
}
