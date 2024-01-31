package com.example.rapgenerator.prompts.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentGeneratingLyrcisBinding
import com.example.rapgenerator.model.ChatcptRequestBody
import com.example.rapgenerator.model.RapChatCptModel
import com.example.rapgenerator.viewmodel.PromptsViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class GeneratingLyrcisFragment : Fragment() {
    private var _binding : FragmentGeneratingLyrcisBinding ?= null
    private val binding get() = _binding!!
    private val viewmodel : PromptsViewModel by viewModels()
    private var rapText = ""
    val args : GeneratingLyrcisFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneratingLyrcisBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rapText = args.text
        viewmodel.promptSendText.observe(viewLifecycleOwner, Observer { response ->
           // handleResponse(response)
        })

        // Call the function to send text
        val text = ChatcptRequestBody("gpt-3.5-turbo",rapText,1,250)
        viewmodel.sendTextToChatGPT(rapText)
       // getData()
    }

   // private fun handleResponse(response: Response<RapChatCptModel>) {
   //     // Handle the response here
   //     when (response) {
   //         is Response.Success -> {
   //             // Handle success
   //             val data = response.data
   //             // Do something with the data
   //         }
   //         is Response.Error -> {
   //             // Handle error
   //             val errorMessage = response.error.localizedMessage
   //             // Display or log the error message
   //         }
   //     }
   // }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}