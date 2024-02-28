package com.example.rapgenerator.view.generating_song

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentGeneratedBinding
import com.example.rapgenerator.databinding.FragmentGeneratingLyrcisBinding
import com.example.rapgenerator.databinding.FragmentGeneratingSongBinding

class GeneratingSongFragment : Fragment() {
    private var _binding: FragmentGeneratingSongBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneratingSongBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}