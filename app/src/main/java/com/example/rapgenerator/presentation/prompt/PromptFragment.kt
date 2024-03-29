package com.example.rapgenerator.presentation.prompt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentPromptBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromptFragment : Fragment(), RapTextAdapter.OnItemClickListener {
    private var _binding: FragmentPromptBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPromptBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRvMoodAndRapText()
    }

    private fun setUpRvMoodAndRapText() {
        val promptMoods = listOf(
            getString(R.string.`fun`),
            getString(R.string.happy),
            getString(R.string.love),
            getString(R.string.sad)
        )
        val prompMoodAdapter = PromptMoodAdapter(requireContext(), promptMoods)
        binding.recyclerviewMood.apply {
            layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
            adapter = prompMoodAdapter
            setHasFixedSize(true)
        }

        val rapTextList = listOf(
            getString(R.string.a_diss_about),
            getString(R.string.a_diss_about),
            getString(R.string.a_diss_about),
            getString(R.string.a_diss_about)
        )

        val rapTextAdapter = RapTextAdapter(rapTextList, this)
        binding.recyclerviewRapText.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = rapTextAdapter
            setHasFixedSize(true)
        }
    }
    override fun onItemClick(rapText: String) {
        binding.etTypeYourPrompts.setText(rapText)

        if (!binding.etTypeYourPrompts.text.isNullOrBlank()) binding.btnContiune.background =
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.button_contiune_background_selected
            )

        binding.btnContiune.setOnClickListener {
            val rapText = binding.etTypeYourPrompts.text.toString()
            if (findNavController().currentDestination?.id == R.id.promptFragment) {
                val action =
                    PromptFragmentDirections.actionPromptFragmentToGeneratingLyrcisFragment(rapText)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
