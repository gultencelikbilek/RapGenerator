package com.example.rapgenerator.view.rapper


import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentRapperBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException

@AndroidEntryPoint
class RapperFragment : Fragment() ,RapperAdapter.RapperItemClickListener{
    private var _binding: FragmentRapperBinding? = null
    private val binding get() = _binding!!
    private val rapperViewModel: RapperViewModel by viewModels()
    private lateinit var rapperAdapter : RapperAdapter
    var mediaPlayer = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRapperBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //rapperViewModel.getRapperRes()
        rapperViewModel.getRapperFlow()
        setUpRv()
        getRapperData()
    }

    private  fun getRapperData() {
       lifecycleScope.launch {
           rapperViewModel.rapperFlow.collect{
               val  firstRapper = it.take(10)
               rapperAdapter.updateData(firstRapper)
           }
       }

        rapperViewModel.rapperResponse.observe(viewLifecycleOwner) { rapperResponseItem ->
            Log.d("success:rapperResponse:", rapperResponseItem.toString())
            val  firstRapper = rapperResponseItem.take(10)
            rapperAdapter.updateData(firstRapper)
        }
    }

    private fun setUpRv() {
        rapperAdapter = RapperAdapter(emptyList(),this)
        binding.rvRapper.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = rapperAdapter
            setHasFixedSize(true)
        }
    }

    override fun rapperItemClick(id: String, isPlaying: Boolean) {
        lifecycleScope.launch{

            binding.btnContiune.setBackgroundResource(R.drawable.button_contiune_background_selected)
            rapperViewModel.getRapperUrl(id)
            Log.d("uuid:rapperfragment:",id)
            rapperViewModel.rapperUrl.collect{

           //    if (isPlaying) {
           //        Log.d("true", "burda")
           //        pauseAudio()
           //    } else {
           //        Log.d("false", "burda")
           //        playAudio(url!!)
           //    }
            }
             binding.btnContiune.setOnClickListener {
                 findNavController().navigate(R.id.action_selectBeatFragment_to_rapperFragment)
             }
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