package com.example.rapgenerator.presentation.song

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rapgenerator.MainActivity
import com.example.rapgenerator.R

class SongFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.setFullscreenFlags()
    }
}