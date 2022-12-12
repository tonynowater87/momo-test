package com.tonynowater.momotest.ui.animalcatalogdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tonynowater.momotest.R
import com.tonynowater.momotest.data.model.ui.AnimalDetailModel
import com.tonynowater.momotest.databinding.ItemAnimalDetailBinding

object AnimalDetailModelDiff : DiffUtil.ItemCallback<AnimalDetailModel>() {
    override fun areItemsTheSame(
        oldItem: AnimalDetailModel, newItem: AnimalDetailModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AnimalDetailModel, newItem: AnimalDetailModel
    ): Boolean {
        return oldItem == newItem
    }
}

class AnimalDetailListAdapter :
    ListAdapter<AnimalDetailModel, AnimalDetailListAdapter.AnimalDetailViewHolder>(
        AnimalDetailModelDiff
    ) {

    var onClickAnimal: ((animalId: Int, title: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalDetailViewHolder {
        return AnimalDetailViewHolder(
            ItemAnimalDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimalDetailViewHolder, position: Int) {
        val animalDetailModel = getItem(position)
        holder.itemView.setOnClickListener {
            onClickAnimal?.invoke(animalDetailModel.id, animalDetailModel.aNameCh)
        }
        holder.bind(animalDetailModel)
    }

    inner class AnimalDetailViewHolder(private val itemAnimalDetailBinding: ItemAnimalDetailBinding) :
        RecyclerView.ViewHolder(itemAnimalDetailBinding.root) {

        fun bind(data: AnimalDetailModel) {
            itemAnimalDetailBinding.textViewName.text = data.aNameCh
            itemAnimalDetailBinding.textViewInfo.text = data.aAlsoKnown.ifEmpty {
                itemView.context.getString(R.string.noAlsoKnown)
            }
            Glide.with(itemView)
                .load(data.aPicture1Url)
                .centerCrop()
                .into(itemAnimalDetailBinding.imageViewPicture)
        }
    }
}