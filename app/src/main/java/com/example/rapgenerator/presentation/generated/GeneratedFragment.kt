package com.example.rapgenerator.presentation.generated

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentGeneratedBinding
import com.example.rapgenerator.utils.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneratedFragment : Fragment() {
    private var _binding : FragmentGeneratedBinding?= null

    private val binding get() = _binding!!
    private var songLyrics : String = ""
    private val args : GeneratedFragmentArgs by navArgs()
    private val mainSharedViewModel : SharedViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentGeneratedBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        onItemClick()
    }

    private fun onItemClick() {
        val rapSongLyrics = binding.tvSongLyricsEdit.text.toString()
        binding.imgEdit.setOnClickListener {
            val action = GeneratedFragmentDirections.actionGeneratedFragmentToEditLyricsFragment(rapSongLyrics)
            findNavController().navigate(action)
        }

        binding.btnContiune.setOnClickListener {
            findNavController().navigate(R.id.action_generatedFragment_to_selectBeatFragment)
        }
    }
    private fun getData() {
        songLyrics = args.songLyrics
        binding.tvSongLyricsEdit.text = songLyrics
        mainSharedViewModel.rapEditLyrics(songLyrics)
        Log.d("songLyrics",songLyrics)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}