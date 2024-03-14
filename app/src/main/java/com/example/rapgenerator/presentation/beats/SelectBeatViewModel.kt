package com.example.rapgenerator.presentation.beats

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapgenerator.data.usecase.GetBeatUrlUseCase
import com.example.rapgenerator.data.usecase.GetBeatsUseCase
import com.example.rapgenerator.domain.ResponseStatus
import com.example.rapgenerator.domain.model.beat.BeatResponse
import com.example.rapgenerator.domain.model.beat.beat_url.BackingTrack
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectBeatViewModel @Inject constructor(
    val getBeatsUseCase: GetBeatsUseCase,
    val getBeatUrlUseCase: GetBeatUrlUseCase
) : ViewModel() {

    private val _selectBeat: MutableStateFlow<BeatResponse?> =
        MutableStateFlow(BeatResponse(emptyList()))
    val selectBeat: StateFlow<BeatResponse?> = _selectBeat

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _beatUrl = MutableStateFlow<BackingTrack?>(null)
    val beatUrl: StateFlow<BackingTrack?> = _beatUrl

    fun getBeat() = viewModelScope.launch {
        // Beats'leri alma UseCase'i çağrılıyor ve sonuçlar bir Flow olarak alınıyor.
        getBeatsUseCase.invoke().collect { response ->
            // Akışta gelen her durum için bir switch-case yapılanması kullanılıyor.
            when (response.responseStatus) {
                // Yükleniyor durumu kontrol ediliyor.
                ResponseStatus.LOADING -> {
                    // Yükleniyor durumu olduğunda isLoading değeri true olarak ayarlanıyor.
                    _isLoading.value = true
                }

                // Başarılı durum kontrol ediliyor.
                ResponseStatus.SUCCESS -> {
                    // Başarılı durumda, gelen veri (_selectBeat) değeri ile güncelleniyor.
                    response.data.let {
                        _selectBeat.value = it
                    }
                    // Yükleniyor durumu bittiğinde isLoading değeri false olarak ayarlanıyor.
                    _isLoading.value = false
                }

                // Hata durumu kontrol ediliyor.
                ResponseStatus.ERROR -> {
                    // Hata durumunda, hatanın loglanması sağlanıyor.
                    Log.d("Uberduck Exc", response.message.toString())
                }
            }
        }
    }


    fun getBeatUrl(uuid: String) = viewModelScope.launch {
        getBeatUrlUseCase.invoke(uuid).collect() { response ->
            when (response.responseStatus) {
                ResponseStatus.LOADING -> {
                    _isLoading.value = true
                }

                ResponseStatus.SUCCESS -> {
                    response.data.let {
                        _beatUrl.value = it!!.backingTrack
                    }
                    _isLoading.value = false
                }

                ResponseStatus.ERROR -> {
                    Log.d("onError:", response.message.toString())
                }
            }
        }
    }
}
