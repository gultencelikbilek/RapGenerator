package com.example.rapgenerator.view.rapper

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.RapperAdapterBinding
import com.example.rapgenerator.domain.model.rapper.RapperResponseItem
import com.example.rapgenerator.view.beats.SelectBeatAdapter

class RapperAdapter(private var rapperImageList: List<RapperResponseItem?>,private val rapperItemClickListener: RapperItemClickListener) : RecyclerView.Adapter<RapperAdapter.RapperViewHolder>() {
    private val playingPositions = mutableListOf<Int>()
    class RapperViewHolder(val binding: RapperAdapterBinding) :
        RecyclerView.ViewHolder(binding.root){
            var isPlaying : Boolean = false
        }

    interface RapperItemClickListener{
        fun rapperItemClick( id:String, isPlaying : Boolean)
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
        val currentItem =  rapperImageList[position]
        holder.binding.apply {
            tvRapperName.text = currentItem!!.display_name
            val tvrapper = tvRapperName.text.toString()
            Log.d("tvRapperName:",tvrapper)
            val rapperImageList = when (position) {
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
            ivRapper.setImageResource(rapperImageList)
        }
        holder.binding.root.setOnClickListener {
            val adapterPlaying = holder.isPlaying
            val adapterPosition = holder.adapterPosition
            if (playingPositions.isNotEmpty()){
                // Eğer önceden başka bir öğe çalınıyorsa, durdur
                val prevPlayingPosition = playingPositions[0]
                playingPositions.clear()
                notifyItemChanged(prevPlayingPosition)
                rapperItemClickListener.rapperItemClick(currentItem!!.voicemodel_uuid,isPlaying = true)
            }

            if (adapterPlaying){
                playingPositions.remove(adapterPosition)
                holder.binding.apply {
                    ivPlayRapper.setImageResource(R.drawable.btn_play_rapper)
                    rapperItemClickListener.rapperItemClick(currentItem!!.voicemodel_uuid,true)
                }
            }else{
                playingPositions.add(adapterPosition)
                holder.binding.apply {
                    ivPlayRapper.setImageResource(R.drawable.btn_pause_rapper)
                    cardRapper.setBackgroundResource(R.drawable.selected_beat_card)
                    rapperItemClickListener.rapperItemClick(currentItem!!.voicemodel_uuid,false)
                }
                holder.isPlaying = adapterPlaying
            }

        }
    }
}