package com.tonynowater.momotest.ui.animalcatalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tonynowater.momotest.R
import com.tonynowater.momotest.data.model.ui.AnimalCatalogModel
import com.tonynowater.momotest.databinding.ItemAnimalCatalogBinding

object AnimalCatalogModelDiff : DiffUtil.ItemCallback<AnimalCatalogModel>() {
    override fun areItemsTheSame(
        oldItem: AnimalCatalogModel, newItem: AnimalCatalogModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AnimalCatalogModel, newItem: AnimalCatalogModel
    ): Boolean {
        return oldItem == newItem
    }
}

class AnimalCatalogListAdapter :
    ListAdapter<AnimalCatalogModel, AnimalCatalogListAdapter.AnimalCatalogViewHolder>(
        AnimalCatalogModelDiff
    ) {

    var onClickCatalog: ((catalogId: Int, title: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalCatalogViewHolder {
        return AnimalCatalogViewHolder(
            ItemAnimalCatalogBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimalCatalogViewHolder, position: Int) {
        val animalCatalogModel = getItem(position)
        holder.itemView.setOnClickListener {
            onClickCatalog?.invoke(animalCatalogModel.id, animalCatalogModel.eName)
        }
        holder.bind(animalCatalogModel)
    }

    inner class AnimalCatalogViewHolder(private val bindingItemAnimalCatalogBinding: ItemAnimalCatalogBinding) :
        RecyclerView.ViewHolder(bindingItemAnimalCatalogBinding.root) {

        fun bind(data: AnimalCatalogModel) {
            bindingItemAnimalCatalogBinding.textViewName.text = data.eName
            bindingItemAnimalCatalogBinding.textViewInfo.text = data.eInfo
            bindingItemAnimalCatalogBinding.textViewMemo.text = data.eMemo.ifEmpty {
                itemView.context.getString(R.string.noMemo)
            }
            Glide.with(itemView)
                .load(data.ePictureUrl)
                .centerCrop()
                .into(bindingItemAnimalCatalogBinding.imageViewPicture)
        }
    }
}