package com.example.rapgenerator.view.beats

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rapgenerator.R
import com.example.rapgenerator.data.beat.BackingTrack
import com.example.rapgenerator.databinding.SelectBeatAdapterBinding

class SelectBeatAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<SelectBeatAdapter.BeatSelectViewHolder>() {
    private val mediaPlayer = MediaPlayer()
    private var isPlaying = false
    private var selectedPosition = RecyclerView.NO_POSITION
    inner class BeatSelectViewHolder(val binding: SelectBeatAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(vaw_audio: String)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<BackingTrack>() {
        override fun areItemsTheSame(oldItem: BackingTrack, newItem: BackingTrack): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: BackingTrack, newItem: BackingTrack): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeatSelectViewHolder {
        return BeatSelectViewHolder(SelectBeatAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BeatSelectViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.tvBeatName.text = currentItem.name

        if (position == selectedPosition) {
            holder.binding.ivSoundVawe.visibility = View.VISIBLE
        } else {
            holder.binding.ivSoundVawe.visibility = View.GONE
        }
        holder.binding.ivPlayBeats.setOnClickListener {
            if (isPlaying) {
                Log.d("burda","ilk buraya girdi")
                mediaPlayer.pause()
                isPlaying = false
                holder.binding.ivPlayBeats.setImageResource(R.drawable.btn_play_beats)
                holder.binding.ivSoundVawe.visibility = View.GONE
            } else {
                mediaPlayer.apply {
                    Log.d("burda","ilk else girdi")
                    reset()
                    setDataSource("https://storage.googleapis.com/vocodes-public/media/a/g/k/z/y/agkzyb72s97v88fbf74d060wstz7ezqj/fakeyou_agkzyb72s97v88fbf74d060wstz7ezqj.wav") // Burada currentItem'in uygun bir ses dosyası yolunu sağladığınızdan emin olun
                    prepare()
                    start()
                    this@SelectBeatAdapter.isPlaying = true
                    holder.binding.ivPlayBeats.setImageResource(R.drawable.btn_pause)
                    holder.binding.ivSoundVawe.visibility = View.VISIBLE
                }
            }
        }
    }

}