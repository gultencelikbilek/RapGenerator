package com.example.rapgenerator.presentation.generating_song

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.rapgenerator.databinding.FragmentGeneratingSongBinding
import com.example.rapgenerator.domain.model.music.MusicRequest
import com.example.rapgenerator.utils.SharedViewModel

class GeneratingSongFragment : Fragment() {
    private var _binding: FragmentGeneratingSongBinding? = null
    private val binding get() = _binding!!
    private val mainSharedViewModel: SharedViewModel by activityViewModels()
    private val generatingSongViewModel: GeneratingSongViewModel by viewModels()

    private var rapSongLyrics: List<List<String>> = emptyList()
    private var voice_model_uuid = "423710f0-00b8-45ea-a349-632991c9d401"
    private var backing_track = ""
    private var musicUrl = ""
    private var rapperName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneratingSongBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataShared()
    }

    private fun getDataShared() {
        mainSharedViewModel.rapEditLyrics.observe(viewLifecycleOwner) { rap ->
            val newRap = rap.trim()
            val paragraph = newRap.split("\n\n")
            rapSongLyrics = paragraph.map { it.split("\n") }
            Log.d("select1", rapSongLyrics.toString())

           //mainSharedViewModel.voiceModelUuid.observe(viewLifecycleOwner) { rapperId ->
           //    voice_model_uuid = rapperId
           //    Log.d("select2", voice_model_uuid)


                mainSharedViewModel.backingTrack.observe(viewLifecycleOwner) { beatUrl ->
                    backing_track = beatUrl
                    Log.d("select3", backing_track)

                    val musicRequest = MusicRequest(backing_track,rapSongLyrics, voice_model_uuid)
                   // generatingSongViewModel.postFreestyle(musicRequest)
                    Log.d("musicrequest", musicRequest.toString())
               // }
            }
        }
        mainSharedViewModel.rapperName.observe(viewLifecycleOwner) {
            binding.tvRapperName.text = it
        }
        mainSharedViewModel.rapperImage.observe(viewLifecycleOwner){
            binding.ivRapper.setImageResource(it)
        }
        generatingSongViewModel.postFreestyle.observe(viewLifecycleOwner) { music ->
            Log.d("bursadda:", "burda")
            if (generatingSongViewModel.postFreestyle.value!!.mix_url==null){
                musicUrl = generatingSongViewModel.postFreestyle.value!!.mix_url.toString()
                Log.d ("musicurl",musicUrl)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
