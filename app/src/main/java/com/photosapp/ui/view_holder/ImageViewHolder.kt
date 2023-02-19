package com.photosapp.ui.view_holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.photosapp.data.local.entities.ImageEntity
import com.photosapp.databinding.ViewRecyclerItemBinding
import com.photosapp.ui.adapter.ImageAdapter

class ImageViewHolder(val binding: ViewRecyclerItemBinding, val listener: ImageAdapter.OnItemClickListener) : ViewHolder(binding.root) {

    fun bind(item: ImageEntity) {
        binding.apply {
            data = item
            executePendingBindings()
            root.setOnClickListener {
                listener.setOnItemClickListener(item)
            }
        }
    }

}