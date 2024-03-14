package com.example.rapgenerator.presentation.beats

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentSelectBeatBinding
import com.example.rapgenerator.utils.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.io.IOException

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class SelectBeatFragment : Fragment(), SelectBeatAdapter.BeatItemClickedListener {
    private var _binding: FragmentSelectBeatBinding? = null
    private val binding get() = _binding!!
    private val selectBeatViewModel: SelectBeatViewModel by viewModels()
    private var selectBeatAdapter = SelectBeatAdapter(this)
    var mediaPlayer = MediaPlayer()
    private val mainSharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectBeatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        selectBeatViewModel.getBeat()
        setUpRv()
        getDataSelectBeat()
    }

    private fun getDataSelectBeat() {
        viewLifecycleOwner.lifecycleScope.launch {
            selectBeatViewModel.selectBeat.collect(){
                selectBeatAdapter.differ.submitList(it!!.backing_tracks)
            }
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
        viewLifecycleOwner.lifecycleScope.launch {
            binding.btnContiune.setBackgroundResource(R.drawable.button_contiune_background_selected)
            selectBeatViewModel.getBeatUrl(uuid)
            mainSharedViewModel.selectBeatBacking_track(uuid)
            Log.d("uuid", uuid)

            selectBeatViewModel.beatUrl.collect { backingTrack ->
                backingTrack?.let { // Null değilse devam et
                    val url = backingTrack.url
                    if (isPLaying) {
                        Log.d("true", "burda")
                        pauseAudio()
                    } else {
                        Log.d("false", "burda")
                        playAudio(url)
                    }
                }
            }
        }
        binding.btnContiune.setOnClickListener {
            pauseAudio()
            findNavController().navigate(R.id.action_selectBeatFragment_to_rapperFragment)
        }
    }
    fun playAudio(url: String?) {
        Log.d("url",url!!)
        try {
            mediaPlayer.apply {
                reset()
                setDataSource(url)
                prepareAsync()
                setOnPreparedListener { mediaPlayer ->
                    mediaPlayer.start()
                }
                setOnErrorListener { _, what, extra ->
                    Log.e("MediaPlayer", "Playback error: what=$what, extra=$extra")
                    false
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun pauseAudio() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}