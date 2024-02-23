package com.example.rapgenerator.view.generating_lyrics


import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rapgenerator.databinding.FragmentGeneratingLyrcisBinding
import com.example.rapgenerator.domain.model.chatgpt.chat.Message
import com.example.rapgenerator.domain.model.chatgpt.chat.ChatGptRequestNew
import com.example.rapgenerator.view.prompt.PromptsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneratingLyrcisFragment : Fragment() {
    private var _binding: FragmentGeneratingLyrcisBinding? = null

    private val binding get() = _binding!!
    private val viewmodel: PromptsViewModel by viewModels()
    private var rapText = ""
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
        countDown()
    }

    private fun countDown() {
        val initialCountDown =  8// Başlangıç sayısı
        val countDownTimer = object :
            CountDownTimer((initialCountDown + 1) * 1000L, 1000L) { // Geri sayım başlangıç süresi
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                binding.tvCountDown.text = secondsLeft.toString()
            }

            override fun onFinish() {
                binding.tvCountDown.text = "0" // Geri sayım bittiğinde 0 göster
                if (binding.tvCountDown.text == "0") {
                    val action =
                        GeneratingLyrcisFragmentDirections.actionGeneratingLyrcisFragmentToGeneratedFragment(
                            rapSongTextMessage
                        )
                    findNavController().navigate(action)
                }
            }
        }
        countDownTimer.start()
    }

    private fun getData() {
        rapText = args.chatRequestBody
        binding.tvRapText.text = rapText.trim()
        messages.add(Message(role = "user", content = rapText))
        val chatGptRequestNew = ChatGptRequestNew(250,"gpt-3.5-turbo-instruct",rapText,0.7)
        viewmodel.sendPromptToChatGPT(chatGptRequestNew)

        viewmodel.promptSendText.observe(viewLifecycleOwner) { chatGptResponse ->
            Log.d("Log","girdi")
            chatGptResponse?.choices?.let { choices ->
                if (choices.isNotEmpty()) {
                    val firstChoice = choices[0]
                    if (firstChoice != null) {
                        val message = firstChoice.text
                        if (!message.isNullOrEmpty()) {
                            rapSongTextMessage = message
                            Log.d("rapsongtext", rapSongTextMessage)
                        }
                    }
                } else {
                    Log.d("rapgenerator", "Choices list is empty")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}