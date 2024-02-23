package com.example.rapgenerator.view.beats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rapgenerator.R
import com.example.rapgenerator.domain.model.chatgpt.beat.BackingTrack
import com.example.rapgenerator.databinding.SelectBeatAdapterBinding

class SelectBeatAdapter(private val beatItemClickedListener: BeatItemClickedListener) :
    RecyclerView.Adapter<SelectBeatAdapter.BeatSelectViewHolder>() {
    private val playingPositions = mutableListOf<Int>()

    inner class BeatSelectViewHolder(val binding: SelectBeatAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val isPlaying: Boolean = false

    }

    interface BeatItemClickedListener {
        fun beatItemClick(uuid: String, isPLaying: Boolean)
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
        return BeatSelectViewHolder(
            SelectBeatAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BeatSelectViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.tvBeatName.text = currentItem.name

        holder.binding.root.setOnClickListener {
            val playing = holder.isPlaying
            // Tıklanan öğenin adapter içindeki pozisyonunu alır.
            val clickedPosition = holder.adapterPosition

            if (playingPositions.isNotEmpty()) {
                // Eğer önceden başka bir öğe çalınıyorsa, durdur
                val prevPlayingPosition = playingPositions[0]
                playingPositions.clear()

                //Önceki oynatılan öğenin görünümünün güncellenmesi için RecyclerView'e haber verir.
                notifyItemChanged(prevPlayingPosition)
                beatItemClickedListener.beatItemClick(uuid = currentItem.uuid, isPLaying = true)
            }
            if (playing) {
                playingPositions.remove(clickedPosition)
                holder.binding.apply {
                    ivPlayBeats.setImageResource(R.drawable.btn_play_beats)
                    ivSoundVawe.visibility = View.GONE
                    beatItemClickedListener.beatItemClick(currentItem.uuid, true)
                }
            } else {
                playingPositions.add(clickedPosition)
                holder.binding.apply {
                    ivPlayBeats.setImageResource(R.drawable.btn_pause)
                    ivSoundVawe.visibility = View.VISIBLE
                    cardBeatSelect.setBackgroundResource(R.drawable.selected_beat_card)
                    beatItemClickedListener.beatItemClick(currentItem.uuid, false)
                }
                holder.isPlaying != playing
            }
        }


    }

}