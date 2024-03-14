package com.example.rapgenerator.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentOnBoardingRapCreatorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingRapCreatorFragment : Fragment() {
    private var _binding: FragmentOnBoardingRapCreatorBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingRapCreatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onItemClick()
    }

    private fun onItemClick() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingRapCreatorFragment_to_onBoardingRapperSelectFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}