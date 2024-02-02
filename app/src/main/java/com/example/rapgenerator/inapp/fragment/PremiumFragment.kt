package com.example.rapgenerator.inapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.FragmentPremiumBinding
import com.example.rapgenerator.utils.ConstantsString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PremiumFragment : Fragment() {
    private var _binding: FragmentPremiumBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPremiumBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onItemClick()
    }

    private fun onItemClick() {

        binding.apply {
            cardWeekly.setOnClickListener { onCardSelected(binding.cardWeekly) }
            cardMonthly.setOnClickListener { onCardSelected(binding.cardMonthly) }
            cardAnnual.setOnClickListener { onCardSelected(binding.cardAnnual) }
            imgClose.setOnClickListener{findNavController().navigate(R.id.action_premiumFragment_to_homeFragment)}
        }
    }

    private fun onCardSelected(cardSelect: CardView) {
        binding.apply {
            cardWeekly.setBackgroundResource(0)
            cardMonthly.setBackgroundResource(0)
            cardAnnual.setBackgroundResource(0)
        }

        cardSelect.setBackgroundResource(R.drawable.custom_card_background)

        binding.btnContiune.setBackgroundResource(
            if (cardSelect.isSelected) 0
            else R.drawable.button_contiune_background_selected
        )
        binding.apply {
            tvRapYour.text = if (cardSelect.isSelected) "" else ConstantsString.getPremium
            tvInspire.text = if (cardSelect.isSelected) "" else ConstantsString.elevatePremium
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}