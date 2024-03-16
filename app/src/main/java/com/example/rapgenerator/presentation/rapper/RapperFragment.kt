package com.example.rapgenerator.presentation.rapper


import android.content.Context
import android.content.SharedPreferences
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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentRapperBinding
import com.example.rapgenerator.utils.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class RapperFragment : Fragment(), RapperAdapter.RapperItemClickListener {
    private var _binding: FragmentRapperBinding? = null
    private val binding get() = _binding!!
    private val rapperViewModel: RapperViewModel by viewModels()
    private lateinit var rapperAdapter: RapperAdapter
    var mediaPlayer = MediaPlayer()
    private val mainSharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRapperBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
        getRapperData()
    }

    private fun getRapperData() {
        viewLifecycleOwner.lifecycleScope.launch {
            rapperViewModel.getRapper() // Rapper verilerini almak için ViewModel'deki fonksiyonu çağırın
            rapperViewModel.rapperResponse.collect { rapperResponseItem ->
                rapperResponseItem?.let {
                    val firstRapper = it.take(10)
                    rapperAdapter.updateData(firstRapper)
                } ?: Log.d("Error", "Rapper response is null")
            }
        }
    }

    private fun setUpRv() {
        rapperAdapter = RapperAdapter(emptyList(), this)
        binding.rvRapper.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = rapperAdapter
            setHasFixedSize(true)
        }
    }

    override fun rapperItemClick(
        id: String, isPlaying: Boolean, rapperName: String, rapperImage: Int, voiceModelUuid: String
    ) {

        lifecycleScope.launch {
            try {
                binding.btnContiune.setBackgroundResource(R.drawable.button_contiune_background_selected)

                mainSharedViewModel.selectVoiceModelUuid(voiceModelUuid)
                mainSharedViewModel.selectRapperName(rapperName)
                mainSharedViewModel.selectRapperImage(rapperImage)

                 rapperViewModel.getRapperUrl(voiceModelUuid)

                rapperViewModel.rapperUrl.collect(){
                    Log.d("RapperFragment:rapperUrl", "UUID: $it")
                }
        }catch (e:Exception){
                Log.d("RapperFragment:rapperUrl", "UUID: $e")
            }
        }

        binding.btnContiune.setOnClickListener {
            findNavController().navigate(R.id.action_rapperFragment_to_generatingSongFragment)
        }
    }

    fun playAudio(url: String) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        } else {
            mediaPlayer.reset()
        }
        try {
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
            mediaPlayer.start()
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