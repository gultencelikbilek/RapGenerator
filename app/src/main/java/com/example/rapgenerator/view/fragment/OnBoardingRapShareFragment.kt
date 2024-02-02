package com.example.rapgenerator.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentOnBoardingRapShareBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingRapShareFragment : Fragment() {
    private var _binding: FragmentOnBoardingRapShareBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingRapShareBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onItemClick()
    }

    private fun onItemClick() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingRapShareFragment_to_premiumFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}