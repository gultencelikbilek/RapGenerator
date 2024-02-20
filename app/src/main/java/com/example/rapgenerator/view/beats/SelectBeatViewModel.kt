package com.example.rapgenerator.view.beats

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.data.beat.BackingTrack
import com.example.rapgenerator.di.AppModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectBeatViewModel @Inject constructor(): ViewModel() {

    private val _selectBeat = MutableLiveData<List<BackingTrack>>() // Değişiklik burada
    val selectBeat: LiveData<List<BackingTrack>> = _selectBeat // Değişiklik burada

    fun getBeat() = viewModelScope.launch {
        val response = AppModule.providesBeatRetrofit().getBeat()
        if (response.isSuccessful){
            response.body()?.let { // Null kontrolü
                _selectBeat.value = listOf(it) // Listeye dönüştürme
            }
        } else {
            Log.d("onError:SelectBeatViewModel:", response.errorBody().toString())
        }
    }
}
