package com.example.rapgenerator.view.beats

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rapgenerator.databinding.FragmentSelectBeatBinding
import java.io.IOException

class SelectBeatFragment : Fragment() ,SelectBeatAdapter.BeatItemClickedListener{
    private var _binding : FragmentSelectBeatBinding ?= null
    private val binding get() = _binding!!
    private val selectBeatViewModel : SelectBeatViewModel by viewModels()
    private var selectBeatAdapter = SelectBeatAdapter(this)
    var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectBeatBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        selectBeatViewModel.getBeat()
        setUpRv()
        getDataSelectBeat()
    }

    private fun getDataSelectBeat() {
        selectBeatViewModel.selectBeat.observe(viewLifecycleOwner) {
          selectBeatAdapter.differ.submitList(it)
        }
    }
    private fun setUpRv() {
        selectBeatAdapter = SelectBeatAdapter(this)
        binding.rvSelectBeat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = selectBeatAdapter
            setHasFixedSize(true)
        }
    }
    override fun beatItemClick(uuid: String, isPLaying: Boolean) {
        selectBeatViewModel.getBeatUrl(uuid)
        selectBeatViewModel.beatUrl.observe(viewLifecycleOwner){
            val url = it.url
            playAudio(url!!)
        }
    }

    fun playAudio(url: String) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        } else {
            mediaPlayer!!.reset()
        }
        try {
            mediaPlayer!!.setDataSource(url)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun pauseAudio() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}