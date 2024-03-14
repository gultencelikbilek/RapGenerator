package com.example.rapgenerator.presentation.edit_lyrics

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rapgenerator.databinding.FragmentEditLyricsBinding


class EditLyricsFragment : Fragment() {
    private var _binding: FragmentEditLyricsBinding? = null
    private val binding get() = _binding!!
    private val args: EditLyricsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditLyricsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onItemClick()
    }

    private fun onItemClick() {
        binding.etRapLyrics.setText(args.rapEditLyrics)

        binding.btnSave.setOnClickListener {
            val newEditRapLyrics = binding.etRapLyrics.text.toString()
            val action = EditLyricsFragmentDirections.actionEditLyricsFragmentToGeneratedFragment(newEditRapLyrics)
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}