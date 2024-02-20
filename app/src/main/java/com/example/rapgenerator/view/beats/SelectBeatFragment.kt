package com.example.rapgenerator.view.beats

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentSelectBeatBinding

class SelectBeatFragment : Fragment() {
    private var _binding : FragmentSelectBeatBinding ?= null
    private val binding get() = _binding!!
    private val selectBeatViewModel : SelectBeatViewModel by viewModels()
    private var selectBeatAdapter = SelectBeatAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectBeatBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
        getDataSelectBeat()
    }

    private fun getDataSelectBeat() {
        selectBeatViewModel.selectBeat.observe(viewLifecycleOwner) {
            it?.let { // Null kontrolü
                selectBeatAdapter.differ.submitList(it)
                Log.d("select",selectBeatAdapter.differ.submitList(it).toString())
            }
        }
    }
    private fun setUpRv() {
        selectBeatAdapter = SelectBeatAdapter()
        binding.rvSelectBeat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = selectBeatAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}