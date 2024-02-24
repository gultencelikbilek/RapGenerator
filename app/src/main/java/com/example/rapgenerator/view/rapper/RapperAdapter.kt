package com.example.rapgenerator.view.rapper

import RapperResponseItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rapgenerator.R
import com.example.rapgenerator.databinding.RapperAdapterBinding

class RapperAdapter : RecyclerView.Adapter<RapperAdapter.RapperViewHolder>() {
    class RapperViewHolder(val binding: RapperAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<RapperResponseItem>() {
        override fun areItemsTheSame(

            oldItem: RapperResponseItem,
            newItem: RapperResponseItem
        ): Boolean {
            return oldItem.voicemodelUuid == newItem.voicemodelUuid
        }

        override fun areContentsTheSame(
            oldItem: RapperResponseItem,
            newItem: RapperResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RapperViewHolder {
        return RapperViewHolder(
            RapperAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RapperViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            tvRapperName.text = currentItem.displayName

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
    }
}