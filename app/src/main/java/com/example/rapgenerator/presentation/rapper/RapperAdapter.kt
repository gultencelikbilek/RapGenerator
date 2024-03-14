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
        val rapperImageResId: Int
        val voicemodel_uuid: String
        holder.binding.apply {
            tvRapperName.text = currentItem!!.display_name
            val tvrapper = tvRapperName.text.toString()
            Log.d("tvRapperName:", tvrapper)
            rapperImageResId = when (position) {
                0 -> R.drawable.img_rapper
                1 -> R.drawable.img_rapper_two
                2 -> R.drawable.img_rapper_four
                3 -> R.drawable.img_raperr_three
                4 -> R.drawable.img_rapper_five
                5 -> R.drawable.img_rapper_six
                6 -> R.drawable.img_rapper_seven
                7 -> R.drawable.img_rapper_eight
                8 -> R.drawable.img_rapper_nine
                else -> {
                    R.drawable.img_rapper
                }
            }
            ivRapper.setImageResource(rapperImageResId)

            voicemodel_uuid = when (position) {
                0 -> "c8a916b4-4574-4042-82a1-3cead35331c9"
                1 -> "114a804a-98b8-4976-84f4-8ea161d0f925"
                2 -> "423710f0-00b8-45ea-a349-632991c9d401"
                3 -> "d42b8a0a-bf24-45a5-b213-fdc7b54f458f"
                4 -> "563c30aa-70c4-4dbc-8c3c-fe7fb2700ee3"
                5 -> "091d6cba-438f-435d-9453-40111feafc99"
                6 -> "c4b48524-c8be-4ad5-ac1a-b4c3c74ae5b5"
                7 -> "c9d65286-329b-4ef3-a508-40817b442990"
                8 -> "109cf329-a698-40ea-9f84-d4c66fdb9f68"
                else -> {
                    "5c14f88a-fa6a-4489-b177-bd948f03e32b"
                }
            }

        }
        holder.binding.root.setOnClickListener {
            val adapterPlaying = holder.isPlaying
            val adapterPosition = holder.adapterPosition
            if (playingPositions.isNotEmpty()) {
                // Eğer önceden başka bir öğe çalınıyorsa, durdur
                val prevPlayingPosition = playingPositions[0]
                playingPositions.clear()
                notifyItemChanged(prevPlayingPosition)
                rapperItemClickListener.rapperItemClick(
                    currentItem!!.voicemodel_uuid,
                    isPlaying = true,
                    name = currentItem.display_name,
                    rapperImage = rapperImageResId,
                    voiceModelUuid = voicemodel_uuid
                )
            }

            if (adapterPlaying) {
                playingPositions.remove(adapterPosition)
                holder.binding.apply {
                    ivPlayRapper.setImageResource(R.drawable.btn_play_rapper)
                    rapperItemClickListener.rapperItemClick(
                        currentItem!!.voicemodel_uuid,
                        true,
                        name = currentItem.display_name,
                        rapperImage = rapperImageResId,
                        voiceModelUuid = voicemodel_uuid
                    )
                }
            } else {
                playingPositions.add(adapterPosition)
                holder.binding.apply {
                    ivPlayRapper.setImageResource(R.drawable.btn_pause_rapper)
                    cardRapper.setBackgroundResource(R.drawable.selected_beat_card)
                    rapperItemClickListener.rapperItemClick(
                        currentItem!!.voicemodel_uuid, false, name = currentItem.display_name,
                        rapperImage = rapperImageResId, voiceModelUuid = voicemodel_uuid
                    )
                }
                holder.isPlaying = adapterPlaying
            }

        }
    }
}