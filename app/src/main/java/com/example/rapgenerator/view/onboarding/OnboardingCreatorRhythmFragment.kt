package com.example.rapgenerator.view.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentOnboardingCreatorRhythmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingCreatorRhythmFragment : Fragment() {
    private var _binding: FragmentOnboardingCreatorRhythmBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding =FragmentOnboardingCreatorRhythmBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onItemClick()
    }

    private fun onItemClick() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingCreatorRhythmFragment_to_onBoardingRapShareFragment)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}