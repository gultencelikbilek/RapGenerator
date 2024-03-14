package com.example.rapgenerator.presentation.prompt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rapgenerator.databinding.RapTextAdapterBinding

class RapTextAdapter(private val rapTextList : List<String>,private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RapTextAdapter.RapTextViewHolder>(){

    class RapTextViewHolder(val binding : RapTextAdapterBinding): RecyclerView.ViewHolder(binding.root)
    interface OnItemClickListener {
        fun onItemClick(rapText: String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RapTextViewHolder {
      return RapTextViewHolder(RapTextAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun getItemCount(): Int {
       return rapTextList.size
    }
    override fun onBindViewHolder(holder: RapTextViewHolder, position: Int) {
        val currentRapText = rapTextList[position]
        holder.binding.apply {
            tvRapText.text = currentRapText.trim()
            root.setOnClickListener {
                onItemClickListener.onItemClick(currentRapText)
            }
        }
    }
}
