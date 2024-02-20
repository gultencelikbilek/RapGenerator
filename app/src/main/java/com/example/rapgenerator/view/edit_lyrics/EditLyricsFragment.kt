package com.example.rapgenerator.view.edit_lyrics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentEditLyricsBinding
import com.example.rapgenerator.viewmodel.SharedViewModel

class EditLyricsFragment : Fragment() {
    private var _binding : FragmentEditLyricsBinding ?= null
    private val binding get() = _binding!!
    private val args : EditLyricsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditLyricsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etRapLyrics.setText(args.rapEditLyrics)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}