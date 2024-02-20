package com.example.rapgenerator.view.beats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rapgenerator.data.beat.BackingTrack
import com.example.rapgenerator.databinding.SelectBeatAdapterBinding

class SelectBeatAdapter : RecyclerView.Adapter<SelectBeatAdapter.BeatSelectViewHolder>() {
    class BeatSelectViewHolder(val binding :SelectBeatAdapterBinding):RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<BackingTrack>(){
        override fun areItemsTheSame(oldItem: BackingTrack, newItem: BackingTrack): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: BackingTrack, newItem: BackingTrack): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeatSelectViewHolder {
        return BeatSelectViewHolder(SelectBeatAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BeatSelectViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            tvBeatName.text = currentItem.name
        }
    }
}