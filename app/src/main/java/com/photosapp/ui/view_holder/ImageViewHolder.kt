package com.photosapp.ui.view_holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.photosapp.data.local.entities.ImageEntity
import com.photosapp.databinding.ViewImageItemBinding


class ImageViewHolder(val binding: ViewImageItemBinding) : ViewHolder(binding.root) {

    fun bind(item: ImageEntity) {
        binding.data = item
        binding.executePendingBindings()
    }

}