package com.example.rapgenerator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel  @Inject constructor():ViewModel(){

    private var _RapEditLyrics =MutableLiveData<String>()
    var rapEditLyrics = _RapEditLyrics


     fun rapEditLyrics(rapEditLyrics: String) {
        _RapEditLyrics.value = rapEditLyrics
    }

}