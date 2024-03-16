package com.example.rapgenerator.presentation.rapper

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.RapperAdapterBinding
import com.example.rapgenerator.domain.model.rapper.RapperResponseItem

class RapperAdapter(
    private var rapperImageList: List<RapperResponseItem?>,
    private val rapperItemClickListener: RapperItemClickListener
) : RecyclerView.Adapter<RapperAdapter.RapperViewHolder>() {
    private val playingPositions = mutableListOf<Int>()

    class RapperViewHolder(val binding: RapperAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isPlaying: Boolean = false
    }

    interface RapperItemClickListener {
        fun rapperItemClick(
            id: String, isPlaying: Boolean,
            name: String,
            rapperImage: Int,
            voiceModelUuid: String
        )
    }

    fun updateData(newList: List<RapperResponseItem?>) {
        rapperImageList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RapperViewHolder {
        return RapperViewHolder(
            RapperAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = rapperImageList.size

    override fun onBindViewHolder(holder: RapperViewHolder, position: Int) {
        val currentItem = rapperImageList[position]
        val rapperImageResId = getRapperImageResId(position)
        val voicemodel_uuid = getVoicemodelUuid(position)

        holder.binding.apply {
            tvRapperName.text = currentItem!!.display_name
            ivRapper.setImageResource(rapperImageResId)
        }

        holder.binding.root.setOnClickListener {
            val adapterPlaying = holder.isPlaying
            val adapterPosition = holder.adapterPosition

            // Eğer önceden başka bir öğe çalınıyorsa, durdur
            if (playingPositions.isNotEmpty()) {
                val prevPlayingPosition = playingPositions[0]
                playingPositions.clear()
                notifyItemChanged(prevPlayingPosition)
            }

            // Öğenin tıklama işlemi
            if (adapterPlaying) {
                playingPositions.remove(adapterPosition)
                holder.binding.ivPlayRapper.setImageResource(R.drawable.btn_play_rapper)
            } else {
                playingPositions.add(adapterPosition)
                holder.binding.ivPlayRapper.setImageResource(R.drawable.btn_pause_rapper)
                holder.binding.cardRapper.setBackgroundResource(R.drawable.selected_beat_card)
            }

            // RapperItemClickListener ile tıklama işlemi gerçekleştir
            rapperItemClickListener.rapperItemClick(
                currentItem!!.voicemodel_uuid,
                !adapterPlaying,
                currentItem.display_name,
                rapperImageResId,
                voicemodel_uuid
            )

            holder.isPlaying = !adapterPlaying
        }
    }

    private fun getRapperImageResId(position: Int): Int {
        return when (position) {
            0 -> R.drawable.img_rapper
            1 -> R.drawable.img_rapper_two
            2 -> R.drawable.img_rapper_four
            3 -> R.drawable.img_raperr_three
            4 -> R.drawable.img_rapper_five
            5 -> R.drawable.img_rapper_six
            6 -> R.drawable.img_rapper_seven
            7 -> R.drawable.img_rapper_eight
            8 -> R.drawable.img_rapper_nine
            else -> R.drawable.img_rapper
        }
    }

    private fun getVoicemodelUuid(position: Int): String {
        return when (position) {
            0 -> "109cf329-a698-40ea-9f84-d4c66fdb9f68"
            1 -> "d42b8a0a-bf24-45a5-b213-fdc7b54f458f"
            2 -> "563c30aa-70c4-4dbc-8c3c-fe7fb2700ee3"
            3 -> "c4b48524-c8be-4ad5-ac1a-b4c3c74ae5b5"
            4 -> "5c14f88a-fa6a-4489-b177-bd948f03e32b"
            5 -> "5a41dfbb-d46e-473c-a5ad-7aac89e10c76"
            6 -> "99667c90-c918-4692-821f-8990b8ff2354"
            7 -> "4ec5c264-0a49-47c8-a3eb-c0d3518a65a1"
            8 -> "92022a27-75fb-4e15-90ca-95095a82f5ee"
            else -> "5c14f88a-fa6a-4489-b177-bd948f03e32b"
        }
    }

}