package com.example.rapgenerator.prompts.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.rapgenerator.databinding.FragmentGeneratingLyrcisBinding
import com.example.rapgenerator.model.ChatGptRequest
import com.example.rapgenerator.model.Message
import com.example.rapgenerator.viewmodel.PromptsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GeneratingLyrcisFragment : Fragment() {
    private var _binding: FragmentGeneratingLyrcisBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: PromptsViewModel by viewModels()
    private var rapText = ""
    private var rapcText = ""
    private var rapTbext = ""
    val args: GeneratingLyrcisFragmentArgs by navArgs()
    private var rapSongTextMessage: String = ""
    val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneratingLyrcisBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    private fun getData() {
        rapText = args.chatRequestBody
        binding.tvRapText.text = rapText
        messages.add(Message(role = "user", content = rapText))
        val chatGptRequest = ChatGptRequest("gpt-3.5-turbo",messages, 1, 250)
        viewmodel.sendPromptToChatGPT(chatGptRequest)

        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.promptSendText.collect {chatGptResponse ->
                chatGptResponse?.choices?.let { choices ->
                    if (choices.isNotEmpty()) {
                        val firstChoice = choices[0]
                        val message = firstChoice?.text
                        if (!(message.isNullOrEmpty())) {
                            rapSongTextMessage = message
                            Log.d("rapsongtext", rapSongTextMessage)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}