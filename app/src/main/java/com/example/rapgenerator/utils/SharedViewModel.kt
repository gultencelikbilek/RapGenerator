package com.example.rapgenerator.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private var _rapEditLyrics = MutableLiveData<String>()
    var rapEditLyrics = _rapEditLyrics

    private var _backingTrack = MutableLiveData<String>()
    var backingTrack = _backingTrack

    private var _voiceModelUuid = MutableLiveData<String>()
    var voiceModelUuid = _voiceModelUuid

    private var _rapperName = MutableLiveData<String>()
    var rapperName = _rapperName

    private var _rapperImage = MutableLiveData<Int>()
    var rapperImage = _rapperImage

    fun rapEditLyrics(rapEditLyrics: String) {
        _rapEditLyrics.value = rapEditLyrics
    }

    fun selectBeatBacking_track(backing_track: String) {
        _backingTrack.value = backing_track
    }

    fun selectVoiceModelUuid(voice_model_uuid: String) {
        _voiceModelUuid.value = voice_model_uuid
    }

    fun selectRapperName(rapperName: String) {
        _rapperName.value = rapperName
    }

    fun selectRapperImage(rapperImage: Int) {
        _rapperImage.value = rapperImage
    }
}