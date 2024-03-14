package com.example.rapgenerator.presentation.prompt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.PromptMoodAdapterBinding

class PromptMoodAdapter(val context : Context, private val promptMoods: List<String>) : RecyclerView.Adapter<PromptMoodAdapter.PromptMoodViewHolder>() {
    private var selectedPosition = RecyclerView.NO_POSITION
    class PromptMoodViewHolder(val binding : PromptMoodAdapterBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromptMoodViewHolder {
       return PromptMoodViewHolder(PromptMoodAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return promptMoods.size
    }
    override fun onBindViewHolder(holder: PromptMoodViewHolder, position: Int) {
        val currentPrompt = promptMoods[position]
        holder.binding.apply {
            btnMood.text = currentPrompt

            if (position == selectedPosition || (position == 0 && selectedPosition == RecyclerView.NO_POSITION)) {
                btnMood.background =
                    ContextCompat.getDrawable(context, R.drawable.custom_recyclerview_mood_background)
                btnMood.setTextColor(ContextCompat.getColor(context, R.color.white))
            } else {
                btnMood.background = null
                btnMood.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            btnMood.setOnClickListener {
                selectedPosition = if (position == selectedPosition) {
                    RecyclerView.NO_POSITION
                } else {
                    position
                }
                notifyDataSetChanged()
            }
        }
    }
}